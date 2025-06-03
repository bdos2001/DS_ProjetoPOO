val carrinho: MutableList<ItemCarrinho> = mutableListOf()

fun menuPecasVendas(){
    var finalizar = false
    while (!finalizar) {
        println("=====================================")
        println("Área de Peças/Vendas")
        println("=====================================")
        println("1. Peças")
        println("2. Compras/Vendas")
        println("0. Sair")
        println("=====================================")
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
    var finalizar = false
    while (!finalizar) {
        println("=====================================")
        println("Área de Peças")
        println("=====================================")
        println("1. Procurar Peças")
        println("2. Carrinho")
        println("3. Finalizar compra")
        println("0. Sair")
        println("=====================================")
        print("Escolha uma opção: ")
        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> procurarPecas()
            2 -> menuCarrinho()
            0 -> finalizar = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun menuCarrinho() {
    var finalizar = false
    while (!finalizar) {
        println("=====================================")
        println("Carrinho de Compras")
        println("=====================================")
        println("1. Adicionar peça ao carrinho")
        println("2. Visualizar carrinho")
        println("3. Editar item do carrinho")
        println("4. Remover item do carrinho")
        println("0. Voltar")
        println("=====================================")
        print("Escolha uma opção: ")
        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> adicionarAoCarrinho()
            2 -> visualizarCarrinho()
            3 -> editarItemCarrinho()
            4 -> removerItemCarrinho()
            0 -> finalizar = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun procurarPecas(){
    var finalizar = false
    while (!finalizar) {
        println("=====================================")
        println("Área de procura de peças")
        println("=====================================")
        println("1. Procurar peça por ID")
        println("2. Procurar peças por tipo de peça")
        println("3. Procurar peças por carro")
        println("4. Procurar peças por carros do cliente")
        println("0. Sair")
        println("=====================================")
        print("Escolha uma opção: ")
        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> procurarPecaPorId()
            2 -> procurarPecaPorTipo()
            3 -> procurarPecaPorCarro()
            4 -> procurarPecaPorCliente()
            0 -> finalizar = true
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

fun procurarPecaPorCarro(){
    cls()
    println("Procurar peças por carro")
    println("Marcas disponíveis:")
    for (marca in listaMarcas) {
        println("ID: ${marca.id}, Nome: ${marca.nome}")
    }
    print("Digite o ID da marca: ")
    val idMarca = readLine()!!.toInt()
    cls()

    val marcaSelecionada = listaMarcas.find { it.id == idMarca }
    if (marcaSelecionada == null) {
        println("Marca com ID $idMarca não encontrada.")
        return
    }

    println("Marca selecionada: ${marcaSelecionada.nome}")
    println("Modelos disponíveis para esta marca:")

    val modelosDaMarca = listaModelos.filter { it.marca.id == idMarca }
    if (modelosDaMarca.isEmpty()) {
        println("Não há modelos disponíveis para esta marca.")
        return
    }

    for (modelo in modelosDaMarca) {
        println("ID: ${modelo.id}, Nome: ${modelo.nome}")
    }

    print("Digite o ID do modelo: ")
    val idModelo = readLine()!!.toInt()
    cls()

    val modeloSelecionado = listaModelos.find { it.id == idModelo }
    if (modeloSelecionado == null) {
        println("Modelo com ID $idModelo não encontrado.")
        return
    }

    println("Modelo selecionado: ${modeloSelecionado.nome}")
    println("Peças compatíveis com este modelo:")

    val pecasCompativeis = listaPecas.filter { peca -> 
        peca.compatibilidadeModelos.any { it.id == idModelo }
    }

    if (pecasCompativeis.isNotEmpty()) {
        println("Peças encontradas para o modelo selecionado:")
        for (peca in pecasCompativeis) {
            println(peca)
        }
    } else {
        println("Nenhuma peça encontrada para o modelo ${modeloSelecionado.nome}.")
    }
}

fun procurarPecaPorCliente(){
    cls()
    println("Procurar peças por carros do cliente")
    print("Digite o ID do cliente: ")
    val idCliente = readLine()!!.toInt()
    cls()

    val cliente = listaPessoas.find { it.idPessoa == idCliente }
    if (cliente == null) {
        println("Cliente com ID $idCliente não encontrado.")
        return
    }

    println("Cliente selecionado: ${cliente.nome}")
    println("Carros do cliente:")

    val carrosDoCliente = listaCarros.filter { it.dono.idPessoa == idCliente }
    if (carrosDoCliente.isEmpty()) {
        println("Este cliente não possui carros registrados.")
        return
    }

    for ((index, carro) in carrosDoCliente.withIndex()) {
        println("${index + 1}. ${carro.modelo.marca.nome} ${carro.modelo.nome} (${carro.matricula})")
    }

    print("Escolha um carro (1-${carrosDoCliente.size}): ")
    val escolhaCarro = readLine()!!.toInt()

    if (escolhaCarro < 1 || escolhaCarro > carrosDoCliente.size) {
        println("Opção inválida.")
        return
    }

    val carroSelecionado = carrosDoCliente[escolhaCarro - 1]
    cls()

    println("Carro selecionado: ${carroSelecionado.modelo.marca.nome} ${carroSelecionado.modelo.nome}")
    println("Peças compatíveis com este modelo:")

    val pecasCompativeis = listaPecas.filter { peca -> 
        peca.compatibilidadeModelos.any { it.id == carroSelecionado.modelo.id }
    }

    if (pecasCompativeis.isNotEmpty()) {
        println("Peças encontradas para o modelo selecionado:")
        for (peca in pecasCompativeis) {
            println(peca)
        }
    } else {
        println("Nenhuma peça encontrada para o modelo ${carroSelecionado.modelo.nome}.")
    }
}

fun adicionarAoCarrinho(){
    cls()
    println("Adicionar peça ao carrinho")
    print("Digite o ID da peça: ")
    val idPeca = readLine()!!.toInt()

    val peca = listaPecas.find { it.id == idPeca }
    if (peca != null) {
        print("Digite a quantidade desejada: ")
        val quantidade = readLine()!!.toInt()

        if (quantidade <= 0) {
            println("A quantidade deve ser maior que zero.")
            return
        }

        if (peca.stock >= quantidade) {
            // Verifica se a peça já está no carrinho
            val itemExistente = carrinho.find { it.peca.id == peca.id }

            if (itemExistente != null) {
                // Se a peça já está no carrinho, atualiza a quantidade
                itemExistente.quantidade += quantidade
                println("Quantidade da peça '${peca.nome}' atualizada no carrinho para ${itemExistente.quantidade}!")
            } else {
                // Se a peça não está no carrinho, adiciona como novo item
                carrinho.add(ItemCarrinho(peca, quantidade))
                println("${quantidade} unidade(s) da peça '${peca.nome}' adicionada(s) ao carrinho com sucesso!")
            }
        } else {
            println("Desculpe, não há estoque suficiente. Estoque disponível: ${peca.stock}")
        }
    } else {
        println("Peça com ID $idPeca não encontrada.")
    }
}

fun visualizarCarrinho() {
    cls()
    println("Carrinho de Compras")
    println("=====================================")

    if (carrinho.isEmpty()) {
        println("O carrinho está vazio.")
    } else {
        var total = 0.0
        println("Itens no carrinho:")
        for ((index, item) in carrinho.withIndex()) {
            val subtotal = item.peca.preco * item.quantidade
            println("${index + 1}. ${item.peca.nome} - ${item.quantidade} unidade(s) x ${item.peca.preco}€ = ${subtotal}€")
            total += subtotal
        }
        println("=====================================")
        println("Total: $total€")
    }

    println("\nPressione Enter para continuar...")
    readLine()
}

fun editarItemCarrinho() {
    cls()
    println("Editar Item do Carrinho")
    println("=====================================")

    if (carrinho.isEmpty()) {
        println("O carrinho está vazio.")
        println("\nPressione Enter para continuar...")
        readLine()
        return
    }

    println("Itens no carrinho:")
    for ((index, item) in carrinho.withIndex()) {
        val subtotal = item.peca.preco * item.quantidade
        println("${index + 1}. ${item.peca.nome} - ${item.quantidade} unidade(s) x ${item.peca.preco}€ = ${subtotal}€")
    }

    print("\nDigite o número do item que deseja editar (1-${carrinho.size}): ")
    val itemIndex = readLine()!!.toInt() - 1

    if (itemIndex < 0 || itemIndex >= carrinho.size) {
        println("Número de item inválido.")
        println("\nPressione Enter para continuar...")
        readLine()
        return
    }

    val item = carrinho[itemIndex]
    println("\nItem selecionado: ${item.peca.nome} - ${item.quantidade} unidade(s)")
    print("Digite a nova quantidade (estoque disponível: ${item.peca.stock}): ")
    val novaQuantidade = readLine()!!.toInt()

    if (novaQuantidade <= 0) {
        println("A quantidade deve ser maior que zero.")
        println("\nPressione Enter para continuar...")
        readLine()
        return
    }

    if (novaQuantidade > item.peca.stock) {
        println("Desculpe, não há estoque suficiente. Estoque disponível: ${item.peca.stock}")
        println("\nPressione Enter para continuar...")
        readLine()
        return
    }

    item.quantidade = novaQuantidade
    println("Quantidade atualizada com sucesso!")
    println("\nPressione Enter para continuar...")
    readLine()
}

fun removerItemCarrinho() {
    cls()
    println("Remover Item do Carrinho")
    println("=====================================")

    if (carrinho.isEmpty()) {
        println("O carrinho está vazio.")
        println("\nPressione Enter para continuar...")
        readLine()
        return
    }

    println("Itens no carrinho:")
    for ((index, item) in carrinho.withIndex()) {
        val subtotal = item.peca.preco * item.quantidade
        println("${index + 1}. ${item.peca.nome} - ${item.quantidade} unidade(s) x ${item.peca.preco}€ = ${subtotal}€")
    }

    print("\nDigite o número do item que deseja remover (1-${carrinho.size}): ")
    val itemIndex = readLine()!!.toInt() - 1

    if (itemIndex < 0 || itemIndex >= carrinho.size) {
        println("Número de item inválido.")
        println("\nPressione Enter para continuar...")
        readLine()
        return
    }

    val itemRemovido = carrinho.removeAt(itemIndex)
    println("Item '${itemRemovido.peca.nome}' removido do carrinho com sucesso!")
    println("\nPressione Enter para continuar...")
    readLine()
}