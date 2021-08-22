fun main() {
    // null 처리하기
    var str1 : String = "Hello Kotlin"
    str1 = null(오류) // null을 허용 하지 않음
    println("str1: $str1")
}
fun main2() {
    // null 처리하기
    var str1 : String? = "Hello Kotlin"
    str1 = null
    println("str1: $str1")
}
fun main3() {
    // null 처리하기
    var str1 : String? = "Hello Kotlin"
    str1 = null
    println("str1: $str1 length : ${str1.length}")
}
fun main4() {
    // null 처리하기
    var str1 : String? = "Hello Kotlin"
    str1 = null
    println("str1: $str1 length : ${str1?.length}")
    // null이 할당되어 있을 가능성이 있는 변수를 검사해 안전하게 호출
    또는
    println("str1: $str1 length : ${str!!.length}")
    // 컴파일러가 null 검사 없이 무시
}
fun main5() {
    var str1 : String? = "Hello Kotlin"
    str1 = null // null을 허용 하지 않음
    val len = if(str1 != null) str1.length else -1
    println("str1: $str1 length: ${len}")
}
fun main6(){
    var str1 : String? = "Hello Kotlin"
    str1 = null // null을 허용 하지 않음
    println("str1: $str1 length: ${str1?.length ?: -1}")
}