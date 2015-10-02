package patterns.composite

interface fileSystem
{
    var name  : String
    fun Rename(name:String){}
    fun Add(type_ : fileSystem)
    fun Remove(name: fileSystem)

}


class File(name: String) : fileSystem
{
    override var name = name
    override  fun Rename(newName : String)
    {
        name = newName
    }
    override fun Add(name: fileSystem){}
    override fun Remove(name: fileSystem){throw Exception("Error")}
}



class Folder(name : String) : fileSystem {

    var list = Array<fileSystem>(0,{File("root")}).toLinkedList()
    override var name = name
    override  fun Rename(newName : String)
    {
        name = newName
    }
    override fun Add(name: fileSystem) {
        list.add(name)
    }
    override fun Remove(name: fileSystem) {
        list.remove(name)
    }
}


fun main(args : Array<String>)
{
    val x = Folder("Music")
    val y = File("AC/DC")
    x.Add(y)
    println(x.name)
    println("   ${x.list.first.name}")
}
