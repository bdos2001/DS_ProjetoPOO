import java.io.File
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

//Listas a ser inicializadas no inicio do programa
var listaClientes: MutableList<Cliente> = mutableListOf()
var listaFuncionarios: MutableList<Funcionario> = mutableListOf()
var listaPessoas: MutableList<Pessoa> = mutableListOf()
var listaCarros: MutableList<Carro> = mutableListOf()
var listaMarcas: MutableList<Marca> = mutableListOf()
var listaModelos: MutableList<Modelo> = mutableListOf()

fun salvarDados() {
    val json = Json { prettyPrint = true }

    File("clientes.json").apply {
        if (!exists()) createNewFile()
        writeText(json.encodeToString(listaClientes))
    }
    File("funcionarios.json").apply {
        if (!exists()) createNewFile()
        writeText(json.encodeToString(listaFuncionarios))
    }
    File("pessoas.json").apply {
        if (!exists()) createNewFile()
        writeText(json.encodeToString(listaPessoas))
    }
    File("marcas.json").apply {
        if (!exists()) createNewFile()
        writeText(json.encodeToString(listaMarcas))
    }
    File("modelos.json").apply {
        if (!exists()) createNewFile()
        writeText(json.encodeToString(listaModelos))
    }
    File("carros.json").apply {
        if (!exists()) createNewFile()
        writeText(json.encodeToString(listaCarros))
    }
}

fun carregarDados() {
    val json = Json { prettyPrint = true }

    if (File("clientes.json").exists()) {
        listaClientes = json.decodeFromString(File("clientes.json").readText())
    }
    if (File("funcionarios.json").exists()) {
        listaFuncionarios = json.decodeFromString(File("funcionarios.json").readText())
    }
    if (File("pessoas.json").exists()) {
        listaPessoas = json.decodeFromString(File("pessoas.json").readText())
    }
    if (File("marcas.json").exists()) {
        listaMarcas = json.decodeFromString(File("marcas.json").readText())
    }
    if (File("modelos.json").exists()) {
        listaModelos = json.decodeFromString(File("modelos.json").readText())
    }
    if (File("carros.json").exists()) {
        listaCarros = json.decodeFromString(File("carros.json").readText())
    }
}

fun carregarFicheiros() {
    // Carregar dados ao iniciar
    carregarDados()

    // Salvar dados ao encerrar o programa
    Runtime.getRuntime().addShutdownHook(Thread {
        salvarDados()
    })
}

fun menu() {
    println("Bem vindo ao sistema de controlo de peças!")
    println("1. Área de Clientes")
    println("2. Área dos funcionários")
    println("3. Área de Carros")
    println("4. Área de Peças/Vendas")
    println("5. Área de Fornecedores/Encomendas")
    println("0. Sair")
    print("Escolha uma opção: ")
}

//Limpa o ecrã só
fun cls() {
    repeat(50) { println() }
}

fun main() {
    carregarFicheiros()
    //Testing
    val cliente1 = Cliente(1,Pessoa(1,"João","Rua A",123456789,123456789,"asdda"))
    val cliente2 = Cliente(2,Pessoa(2,"Maria","Rua B",987654321,987654321,"asdda"))
    val funcionario1 = Funcionario(1,Pessoa(3,"Pedro","Rua C",123456789,123456789,"asdda"))
    val funcionario2 = Funcionario(2,Pessoa(4,"Ana","Rua D",987654321,987654321,"asdda"))
    val marca1 = Marca(1,"Seat")
    val marca2 = Marca(2,"Audi")
    val modelo1 = Modelo(1,"Ibiza",marca1)
    val modelo2 = Modelo(2,"A3",marca2)
    val modelo3 = Modelo(3,"Toledo",marca1)
    listaPessoas.add(cliente1.pessoa)
    listaPessoas.add(cliente2.pessoa)
    listaPessoas.add(funcionario1.pessoa)
    listaPessoas.add(funcionario2.pessoa)
    listaClientes.add(cliente1)
    listaClientes.add(cliente2)
    listaFuncionarios.add(funcionario1)
    listaFuncionarios.add(funcionario2)
    listaMarcas.add(marca1)
    listaMarcas.add(marca2)
    listaModelos.add(modelo1)
    listaModelos.add(modelo2)
    listaModelos.add(modelo3)
    listaCarros.add(Carro("12-34-AB", modelo1, 2023, cliente1.pessoa))
    listaCarros.add(Carro("12-35-AB", modelo1, 2020, cliente1.pessoa))
    listaCarros.add(Carro("12-36-AB", modelo2, 2024, cliente2.pessoa))
    listaCarros.add(Carro("12-37-AB", modelo3, 2025, cliente2.pessoa))
    listaCarros.add(Carro("12-38-AB", modelo1, 2021, funcionario1.pessoa))
    //Testing

    var opcMenu: Int = -1
    while(opcMenu != 0) {
        cls()
        menu()
        opcMenu = readLine()!!.toInt()
        cls()
        when (opcMenu) {
            1 -> menuClientes()
            2 -> menuAreaFuncionarios()
            3 -> menuAreaCarros()
            4 -> println("Peças/Vendas")
            5 -> println("Fornecedores/Encomendas")
            0 -> {
                println("A encerrar o programa")
                salvarDados()
            }
            else -> println("Opção inválida")
        }

    }
}

fun mostrarPessoas() {
    for (pessoa in listaPessoas) {
        val tipo = when {
            listaClientes.any { it.pessoa.idPessoa == pessoa.idPessoa } -> "Cliente"
            listaFuncionarios.any { it.pessoa.idPessoa == pessoa.idPessoa } -> "Funcionário"
            else -> "Não especificado"
        }
        println("ID Pessoa: ${pessoa.idPessoa}, Nome: ${pessoa.nome}, Tipo: $tipo")
    }
}