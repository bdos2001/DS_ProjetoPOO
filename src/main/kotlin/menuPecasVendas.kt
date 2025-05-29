fun menuPecasVendas(){
    var finalizar = false
    while (!finalizar) {
        println("Área de Peças/Vendas")
        println("1. Peças")
        println("2. Compras/Vendas")
        println("0. Sair")
        print("Escolha uma opção: ")
        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> pecas()
            2 -> println("Compras")
            0 -> finalizar = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun pecas(){
    var finalizar1 = false
    while (!finalizar1) {
        println("Área de Peças")
        println("1. Procurar Peças")
        println("2. Adicionar peça ao Carrinho")
        println("3. Visualizar carrinho")
        println("4. Finalizar compra")
        println("0. Sair")
        print("Escolha uma opção: ")
        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> procurarPecas()
            2 -> println("Adicionar peça ao carrinho por ID")
            3 -> println("Visualizar carrinho")
            0 -> finalizar1 = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun procurarPecas(){
    var finalizar2 = false
    while (!finalizar2) {
        println("Área de procura de peças")
        println("1. Procurar peça por ID")
        println("2. Procurar peças por tipo de peça")
        println("3. Procurar peças por carro")
        println("4. Procurar peças por carros de cliente")
        println("0. Sair")
        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> procurarPecaPorId()
            2 -> procurarPecaPorTipo()
            3 -> println("Procurar peça por carro")
            4 -> println("Procurar peça por carro de cliente")
            0 -> finalizar2 = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun procurarPecaPorId() {
    cls()
    println("Procurar peça por ID")
    print("Digite o ID da peça: ")
    val idPeca = readLine()!!.toInt()
    val peca = listaPecas.find { it.id == idPeca }
    cls()
    if (peca != null) {
        println("Peça encontrada:")
        println(peca)
    } else {
        println("Peça com ID $idPeca não encontrada.")
    }
}

fun procurarPecaPorTipo() {
    cls()
    println("Procurar peças por tipo de peça")
    println("Tipos de peças disponíveis:")
    for (tipo in listaTipoPecas) {
        println("ID: ${tipo.id}, Nome: ${tipo.nome}")
    }
    print("Digite o ID do tipo de peça: ")
    val idTipo = readLine()!!.toInt()
    cls()
    val pecasDoTipo = listaPecas.filter { it.tipoPeca.id == idTipo }
    if (pecasDoTipo.isNotEmpty()) {
        println("Peças encontradas para o tipo selecionado:")
        for (peca in pecasDoTipo) {
            println(peca)
        }
    } else {
        println("Nenhuma peça encontrada para o tipo com ID $idTipo.")
    }
}