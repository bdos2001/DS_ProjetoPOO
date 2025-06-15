import java.io.File
import kotlinx.serialization.json.Json

var listaClientes: MutableList<Cliente> = mutableListOf()
var listaFuncionarios: MutableList<Funcionario> = mutableListOf()
var listaPessoas: MutableList<Pessoa> = mutableListOf()
var listaCarros: MutableList<Carro> = mutableListOf()
var listaMarcas: MutableList<Marca> = mutableListOf()
var listaModelos: MutableList<Modelo> = mutableListOf()
var listaPecas: MutableList<Peca> = mutableListOf()
var listaTipoPecas: MutableList<TipoPeca> = mutableListOf()
var listaComprasCliente: MutableList<ComprasCliente> = mutableListOf()
var listaFornecedores: MutableList<Fornecedor> = mutableListOf()
var listaEncomendasFornecedor: MutableList<EncomendaFornecedor> = mutableListOf()

fun guardarFicheiros() {
    val json = Json { prettyPrint = true }

    File("clientes.json").writeText(json.encodeToString(listaClientes))
    File("funcionarios.json").writeText(json.encodeToString(listaFuncionarios))
    File("pessoas.json").writeText(json.encodeToString(listaPessoas))
    File("carros.json").writeText(json.encodeToString(listaCarros))
    File("marcas.json").writeText(json.encodeToString(listaMarcas))
    File("modelos.json").writeText(json.encodeToString(listaModelos))
    File("pecas.json").writeText(json.encodeToString(listaPecas))
    File("tipoPecas.json").writeText(json.encodeToString(listaTipoPecas))
    File("comprasCliente.json").writeText(json.encodeToString(listaComprasCliente))
    File("fornecedores.json").writeText(json.encodeToString(listaFornecedores))
    File("encomendasFornecedor.json").writeText(json.encodeToString(listaEncomendasFornecedor))

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
        listaPecas = File("pecas.json").takeIf { it.exists() }?.readText()?.let { json.decodeFromString(it) } ?: mutableListOf()
        listaTipoPecas = File("tipoPecas.json").takeIf { it.exists() }?.readText()?.let { json.decodeFromString(it) } ?: mutableListOf()
        listaComprasCliente = File("comprasCliente.json").takeIf { it.exists() }?.readText()?.let { json.decodeFromString(it) } ?: mutableListOf()
        listaFornecedores = File("fornecedores.json").takeIf { it.exists() }?.readText()?.let { json.decodeFromString(it) } ?: mutableListOf()
        listaEncomendasFornecedor = File("encomendasFornecedor.json").takeIf { it.exists() }?.readText()?.let { json.decodeFromString(it) } ?: mutableListOf()

        println("Dados carregados com sucesso!")
    } catch (e: Exception) {
        println("Erro ao carregar os ficheiros: ${e.message}")
    }
}

fun menu() {
    println("=====================================")
    println("Bem vindo ao sistema de controlo de peças!")
    println("=====================================")
    println("1. Área de Carros e Cliente")
    println("2. Área de Peças/Vendas")
    println("3. Área de Fornecedores/Encomendas")
    println("9. Admin")
    println("0. Sair")
    println("=====================================")
    print("Escolha uma opção: ")
}

fun cls() {
    repeat(50) { println() }
}

fun main() {
    carregarFicheiros()

    var opcMenu: Int = -1
    while(opcMenu != 0) {
        cls()
        menu()
        try {
            opcMenu = readLine()!!.toInt()
            when (opcMenu) {
                1 -> menuCarroouCliente()
                2 -> menuPecasVendas()
                3 -> menuFornecedoresEncomendas()
                9 -> menuAdmin()
                0 -> {
                    println("A encerrar o programa")
                    guardarFicheiros()
                }
                else -> println("Opção inválida, tente novamente.")
            }
        } catch (e: NumberFormatException) {
            cls()
            println("Erro: Por favor, insira um número válido.")
        }
    }
}

fun listarPessoas() {
    for (pessoa in listaPessoas) {
        val tipo = when {
            listaClientes.any { it.pessoa.idPessoa == pessoa.idPessoa } -> "Cliente"
            listaFuncionarios.any { it.pessoa.idPessoa == pessoa.idPessoa } -> "Funcionário"
            else -> "Não especificado"
        }
        println("ID Pessoa: ${pessoa.idPessoa}, Nome: ${pessoa.nome}, Tipo: $tipo")
    }
}

fun menuCarroouCliente() {
    println("=====================================")
    println("Área de Carros e Clientes")
    println("=====================================")
    println("1. Área de Carros")
    println("2. Área de Clientes")
    println("0. Voltar")
    println("=====================================")
    print("Escolha uma opção: ")
    try {
        val opc = readLine()!!.toInt()
        cls()
        when (opc) {
            1 -> menuAreaCarros()
            2 -> menuClientes()
            0 -> return
            else -> println("Opção inválida, tente novamente.")
        }
    } catch (e: NumberFormatException) {
        cls()
        println("Erro: Por favor, insira um número válido.")
        menuCarroouCliente()
    }
}
