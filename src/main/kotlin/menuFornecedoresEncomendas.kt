
fun menuFornecedoresEncomendas() {
    println("=====================================")
    println("Área de Fornecedores e Encomendas")
    println("=====================================")
    println("1. Listar Fornecedores")
    println("2. Fazer encomenda a Fornecedor")
    println("3. Listar Encomendas")
    println("0. Voltar")
    println("=====================================")
    print("Escolha uma opção: ")
    val opc = readLine()!!.toInt()
    cls()
    when (opc) {
        1 -> listarTodosFornecedores()
        2 -> fazerEncomendaFornecedor()
        3 -> listarEncomendas()
        0 -> return
        else -> println("Opção inválida, tente novamente.")
    }
}

fun listarTodosFornecedores() {
    println("=====================================")
    println("Lista de Fornecedores")
    println("=====================================")
    if (listaFornecedores.isEmpty()) {
        println("Não existem fornecedores registados.")
    } else {
        for (fornecedor in listaFornecedores) {
            println(fornecedor)
        }
    }
    println("\nPressione ENTER para continuar...")
    readLine()
}

fun listarEncomendas() {
    println("=====================================")
    println("Lista de Encomendas a Fornecedores")
    println("=====================================")
    if (listaEncomendasFornecedor.isEmpty()) {
        println("Não existem encomendas registadas.")
    } else {
        for (encomenda in listaEncomendasFornecedor) {
            println(encomenda)
            println()
        }
    }
    println("\nPressione ENTER para continuar...")
    readLine()
}

fun fazerEncomendaFornecedor() {
    if (listaFornecedores.isEmpty()) {
        println("Não existem fornecedores registados. Impossível fazer encomenda.")
        println("\nPressione ENTER para continuar...")
        readLine()
        return
    }

    if (listaPecas.isEmpty()) {
        println("Não existem peças registadas. Impossível fazer encomenda.")
        println("\nPressione ENTER para continuar...")
        readLine()
        return
    }

    // Selecionar fornecedor
    println("=====================================")
    println("Selecione um fornecedor:")
    println("=====================================")
    for ((index, fornecedor) in listaFornecedores.withIndex()) {
        println("${index + 1}. ${fornecedor.nome} (${fornecedor.pais})")
    }
    print("Escolha uma opção (0 para cancelar): ")
    val opcFornecedor = readLine()!!.toInt()

    if (opcFornecedor == 0) return
    if (opcFornecedor < 1 || opcFornecedor > listaFornecedores.size) {
        println("Opção inválida.")
        return
    }

    val fornecedorSelecionado = listaFornecedores[opcFornecedor - 1]
    val carrinhoEncomenda = mutableListOf<ItemCarrinho>()
    var continuarEncomenda = true

    while (continuarEncomenda) {
        cls()
        println("=====================================")
        println("Encomenda para: ${fornecedorSelecionado.nome}")
        println("=====================================")
        println("Itens no carrinho: ${carrinhoEncomenda.size}")
        println("1. Adicionar item")
        println("2. Editar quantidade de item")
        println("3. Remover item")
        println("4. Ver carrinho")
        println("5. Finalizar encomenda")
        println("0. Cancelar encomenda")
        println("=====================================")
        print("Escolha uma opção: ")

        when (readLine()!!.toInt()) {
            1 -> adicionarItemEncomenda(carrinhoEncomenda)
            2 -> editarItemEncomenda(carrinhoEncomenda)
            3 -> removerItemEncomenda(carrinhoEncomenda)
            4 -> verCarrinhoEncomenda(carrinhoEncomenda)
            5 -> {
                if (finalizarEncomenda(fornecedorSelecionado, carrinhoEncomenda)) {
                    continuarEncomenda = false
                }
            }
            0 -> {
                println("Encomenda cancelada.")
                continuarEncomenda = false
            }
            else -> println("Opção inválida.")
        }
    }
}

fun adicionarItemEncomenda(carrinho: MutableList<ItemCarrinho>) {
    cls()
    println("=====================================")
    println("Adicionar Item à Encomenda")
    println("=====================================")

    // Listar peças disponíveis
    for ((index, peca) in listaPecas.withIndex()) {
        println("${index + 1}. ${peca.nome} (${peca.tipoPeca.nome})")
    }

    print("Selecione uma peça (0 para cancelar): ")
    val opcPeca = readLine()!!.toInt()

    if (opcPeca == 0) return
    if (opcPeca < 1 || opcPeca > listaPecas.size) {
        println("Opção inválida.")
        println("\nPressione ENTER para continuar...")
        readLine()
        return
    }

    val pecaSelecionada = listaPecas[opcPeca - 1]

    // Verificar se a peça já está no carrinho
    val itemExistente = carrinho.find { it.peca.id == pecaSelecionada.id }
    if (itemExistente != null) {
        println("Esta peça já está no carrinho. Use a opção 'Editar quantidade' para alterar.")
        println("\nPressione ENTER para continuar...")
        readLine()
        return
    }

    print("Quantidade: ")
    val quantidade = readLine()!!.toInt()

    if (quantidade <= 0) {
        println("A quantidade deve ser maior que zero.")
        println("\nPressione ENTER para continuar...")
        readLine()
        return
    }

    carrinho.add(ItemCarrinho(pecaSelecionada, quantidade))
    println("Item adicionado ao carrinho.")
    println("\nPressione ENTER para continuar...")
    readLine()
}

fun editarItemEncomenda(carrinho: MutableList<ItemCarrinho>) {
    if (carrinho.isEmpty()) {
        println("O carrinho está vazio.")
        println("\nPressione ENTER para continuar...")
        readLine()
        return
    }

    cls()
    println("=====================================")
    println("Editar Item da Encomenda")
    println("=====================================")

    for ((index, item) in carrinho.withIndex()) {
        println("${index + 1}. ${item.peca.nome} - ${item.quantidade} unidade(s)")
    }

    print("Selecione um item para editar (0 para cancelar): ")
    val opcItem = readLine()!!.toInt()

    if (opcItem == 0) return
    if (opcItem < 1 || opcItem > carrinho.size) {
        println("Opção inválida.")
        println("\nPressione ENTER para continuar...")
        readLine()
        return
    }

    val itemSelecionado = carrinho[opcItem - 1]

    print("Nova quantidade (atual: ${itemSelecionado.quantidade}): ")
    val novaQuantidade = readLine()!!.toInt()

    if (novaQuantidade <= 0) {
        println("A quantidade deve ser maior que zero. Use a opção 'Remover item' para remover.")
        println("\nPressione ENTER para continuar...")
        readLine()
        return
    }

    itemSelecionado.quantidade = novaQuantidade
    println("Quantidade atualizada.")
    println("\nPressione ENTER para continuar...")
    readLine()
}

fun removerItemEncomenda(carrinho: MutableList<ItemCarrinho>) {
    if (carrinho.isEmpty()) {
        println("O carrinho está vazio.")
        println("\nPressione ENTER para continuar...")
        readLine()
        return
    }

    cls()
    println("=====================================")
    println("Remover Item da Encomenda")
    println("=====================================")

    for ((index, item) in carrinho.withIndex()) {
        println("${index + 1}. ${item.peca.nome} - ${item.quantidade} unidade(s)")
    }

    print("Selecione um item para remover (0 para cancelar): ")
    val opcItem = readLine()!!.toInt()

    if (opcItem == 0) return
    if (opcItem < 1 || opcItem > carrinho.size) {
        println("Opção inválida.")
        println("\nPressione ENTER para continuar...")
        readLine()
        return
    }

    carrinho.removeAt(opcItem - 1)
    println("Item removido do carrinho.")
    println("\nPressione ENTER para continuar...")
    readLine()
}

fun verCarrinhoEncomenda(carrinho: MutableList<ItemCarrinho>) {
    cls()
    println("=====================================")
    println("Carrinho de Encomenda")
    println("=====================================")

    if (carrinho.isEmpty()) {
        println("O carrinho está vazio.")
    } else {
        for ((index, item) in carrinho.withIndex()) {
            println("${index + 1}. ${item.peca.nome} - ${item.quantidade} unidade(s)")
        }
    }

    println("\nPressione ENTER para continuar...")
    readLine()
}

fun finalizarEncomenda(fornecedor: Fornecedor, carrinho: MutableList<ItemCarrinho>): Boolean {
    if (carrinho.isEmpty()) {
        println("O carrinho está vazio. Impossível finalizar a encomenda.")
        println("\nPressione ENTER para continuar...")
        readLine()
        return false
    }

    cls()
    println("=====================================")
    println("Finalizar Encomenda")
    println("=====================================")
    println("Fornecedor: ${fornecedor.nome}")
    println("Itens:")

    for (item in carrinho) {
        println("- ${item.peca.nome}: ${item.quantidade} unidade(s)")
    }

    println("\nConfirmar encomenda? (S/N)")
    val confirmacao = readLine()!!.uppercase()

    if (confirmacao != "S") {
        println("Encomenda não confirmada.")
        println("\nPressione ENTER para continuar...")
        readLine()
        return false
    }

    // Gerar ID para a encomenda
    val idEncomenda = if (listaEncomendasFornecedor.isEmpty()) 1 else listaEncomendasFornecedor.maxOf { it.idEncomenda } + 1

    // Obter data atual (formato dd/mm/aaaa)
    val dataAtual = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    // Criar a encomenda
    val encomenda = EncomendaFornecedor(
        idEncomenda = idEncomenda,
        fornecedor = fornecedor,
        itensEncomendados = carrinho.toList(),
        dataEncomenda = dataAtual
    )

    // Adicionar à lista de encomendas
    listaEncomendasFornecedor.add(encomenda)

    // Atualizar o stock das peças
    for (item in carrinho) {
        val peca = listaPecas.find { it.id == item.peca.id }
        if (peca != null) {
            peca.stock += item.quantidade
        }
    }

    // Guardar os dados
    guardarFicheiros()

    println("Encomenda finalizada com sucesso!")
    println("\nPressione ENTER para continuar...")
    readLine()
    return true
}
