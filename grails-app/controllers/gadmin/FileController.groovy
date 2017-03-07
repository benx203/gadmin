package gadmin

import grails.converters.JSON
import org.apache.commons.fileupload.servlet.ServletFileUpload

class FileController {

    private static Map<String, List<String>> extMap = new HashMap<String, List<String>>();// 允许上传的文件扩展名

    private static long maxSize = 1024 * 1024 * 10 // 允许上传最大文件大小(字节)

    static String rootPath = null// 文件保存目录路径
    static final String rootUrl = '/upload/'// 文件保存目录URL

    def servletContext

    void setServletContext(servletContext) {
        this.servletContext = servletContext
        rootPath = servletContext.getRealPath("")// 文件保存目录路径
    }

    static {
        String[] uploadTypes = 'image:gif,jpg,jpeg,png,bmp;flash:swf,flv;media:swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb;file:doc,docx,xls,xlsx,ppt,txt,zip,rar,gif,jpg,jpeg,png,bmp,pdf;docs:doc,docx,xls,xlsx;docx:docx'.split(";");
        for (String uploadType : uploadTypes) {
            String[] arr = uploadType.split(":");
            extMap.put(arr[0], Arrays.asList(arr[1].split(",")));
        }
    }

    def upload() {
        def m = [:]
        m.put("error", 1)
        m.put("message", "上传失败！")

        if (!ServletFileUpload.isMultipartContent(request)) {
            m.put("message", "请选择文件！");
            render m as JSON
            return
        }

        String dirName = params.get("dir","file");
        String fileType = params.get("type","file");
        boolean changeName = params.getBoolean("changeName",true);// 因为中文名默认情况下会找不到文件，所以默认为true使用uuid重命名

        String fileUrl = rootUrl + dirName + "/";
        String filePath = rootPath + fileUrl;

        // 创建文件夹
        File dirFile = new File(filePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        def item = request.getFile('file')

        if (item.empty) {
            m.put("message", "文件不能为空！");
            render m as JSON
            return
        }

        String fileName = item.getFileItem().getName().toLowerCase();// 文件名转为小写（Tomcat7不支持大写）
        // 检查文件大小
        if (item.getSize() > maxSize) {
            m.put("message", "上传文件大小超过限制！(允许最大[" + maxSize + "]字节，您上传了[" + item.getSize() + "]字节)");
            render m as JSON
            return
        }

        // 检查扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        List<String> allowTypes = extMap.get(fileType);
        if (!allowTypes.contains(fileExt)) {
            m.put("message", "上传文件扩展名是不允许的扩展名。\n只允许" + allowTypes + "格式！");
            render m as JSON
            return
        }

        String newFileName = fileName;
        newFileName = newFileName.replace(" ","");
        newFileName = newFileName.trim();
        newFileName = newFileName.substring(0,newFileName.lastIndexOf(".")).toLowerCase();
        if(changeName){
            newFileName = newFileName + "_" + DateUtil.shortTimeStamp();// 给文件名加上版本号（当前时间），避免同名冲突
        }
        newFileName = newFileName + "." + fileExt;
        item.transferTo(new File(filePath + newFileName))
        m.put("error", 0);
        m.put("url", fileUrl + newFileName);
        m.put("name",newFileName);
        render m as JSON
    }
}
