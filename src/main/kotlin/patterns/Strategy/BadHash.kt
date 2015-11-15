package patterns.Strategy

public fun BadHash(input : String) : String{
    val hasher : MD5  = MD5()
    return hasher.computeHash(input)
}

 fun main(args: Array<String>) {

}