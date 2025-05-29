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

fun guardarFicheiros() {
    val json = Json { prettyPrint = true }

    File("clientes.json").writeText(json.encodeToString(listaClientes))
    File("funcionarios.json").writeText(json.encodeToString(listaFuncionarios))
    File("pessoas.json").writeText(json.encodeToString(listaPessoas))
    File("carros.json").writeText(json.encodeToString(listaCarros))
    File("marcas.json").writeText(json.encodeToString(listaMarcas))
    File("modelos.json").writeText(json.encodeToString(listaModelos))

    println("Dados guardados com sucesso!")
}

fun carregarFicheiros() {
    val json = Json { prettyPrint = true }

    try {
        listaClientes = File("clientes.json").takeIf { it.exists() }?.readText()?.let { json.decodeFromString(it) } ?: mutableListOf()
        listaFuncionarios = File("funcionarios.json").takeIf { it.exists() }?.readText()?.let { json.decodeFromString(it) } ?: mutableListOf()
        listaPessoas = File("pessoas.json").takeIf { it.exists() }?.readText()?.let { json.decodeFromString(it) } ?: mutableListOf()
        listaCarros = File("carros.json").takeIf { it.exists() }?.readText()?.let { json.decodeFromString(it) } ?: mutableListOf()
        listaMarcas = File("marcas.json").takeIf { it.exists() }?.readText()?.let { json.decodeFromString(it) } ?: mutableListOf()
        listaModelos = File("modelos.json").takeIf { it.exists() }?.readText()?.let { json.decodeFromString(it) } ?: mutableListOf()

        println("Dados carregados com sucesso!")
    } catch (e: Exception) {
        println("Erro ao carregar os ficheiros: ${e.message}")
    }
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
                guardarFicheiros()
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