package patterns.Strategy

public interface IHasher {
    public fun computeHash(password : String) : String
}

//not best implementation
public fun getHash(input : String, hasher : IHasher) : String {
    return hasher.computeHash(input)
}
public fun getHash(input : String) : String {
    return MD5().computeHash(input)
}

//the optimal one
public class Context {
    private var strategy : IHasher = MD5()
    public constructor() {
    }
    public constructor(str : IHasher) {
        strategy = str
    }
    public fun executeStrategy(input : String) : String {
        return strategy.computeHash(input);
    }
}

fun main(args: Array<String>) {
    //not best implementation
    var str = getHash("Web Biscuit")
    str = getHash("Web Biscuit", SHA256() )
    //the traditional one
    var context = Context()
    println("MD5     is ${context.executeStrategy("Web Biscuit")}")
    context = Context(SHA256())
    println("SHA-256 is ${context.executeStrategy("Web Biscuit")}")
}
