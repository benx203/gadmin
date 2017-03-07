package gadmin

import grails.test.GrailsUnitTestCase


class MyTest extends GrailsUnitTestCase{

    void test(){
        def list = ['user:delete','user:create','user:edit','user:list']

        list.findAll{it.contains(':create')}.each {
            String[] arr = it.split(':')
            list.add(arr[0] + ':save')
        }

        list.findAll{it.contains(':edit')}.each {
            String[] arr = it.split(':')
            list.add(arr[0] + ':update')
        }

        println list
    }

    void test2(){
        println null?true:false
        println ''?true:false
        println 'aa'?true:false
    }

    void testList(){
        def arr = ['aa','bb','cc']
        println(arr.join(','))
    }

    void testEmpty(){
        def a = []
        if(a){
            println('not empty')
        }
    }
}
