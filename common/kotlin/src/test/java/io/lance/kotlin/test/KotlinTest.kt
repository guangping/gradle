package io.lance.kotlin.test

import io.lance.kotlin.test.vo.Person
import io.lance.kotlin.test.vo.User

/**
 * @author lance.
 * @time: 2018-06-01 14:50
 * @desc:
 */

fun add(a: Int, b: Int): Int {
    return a + b;
}


fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun select(obj: String): String? {
    when (obj) {
        "a" -> return "1";
        "b" -> return "2";
        else -> return "Unknown"
    }
    return null;
}

fun main(args: Array<String>) {
    var person = Person();
    println(person)

    //var user = User("beijing", 13);


}

/*var map = mapOf<String, Int>("1" to 1);
println(map.get("2"));*/

/*
val fruits = listOf("banana", "avocado", "apple", "kiwi")
fruits.filter { it.startsWith("a") }.forEach({
    println(it)
})
*/

/*
  var sets= setOf<String>("a","b","a","c");
    println(sets.size)

var user=User("fff",14);
println(user)
*/

/*
var x= select("dddd");
println(x)
*/

/*val items = setOf("apple", "banana", "kiwi")
when {
    "orange" in items -> println("juicy")
    "apple" in items -> println("apple is fine too")
}*/

/*
for (x in 1..5) {
        println(x)
    }


println(add(3, 5))

println("result is : ${add(0, 42)}")

   var x:Int=5;
    x+=1;
    println(x)*/
/* var x= parseInt("a");
 var y= parseInt("354")
 println("$x,$y");*/

/*  var list = listOf("a", "vb", "c");
  for (x in list) {
      println(x)
  }*/