fun menuAdmin() {
    var finalizar = false
    while (!finalizar) {
        println("=====================================")
        println("Bem-vindo ao Menu de Administração!")
        println("=====================================")
        println("1. Gerir Funcionários")
        println("2. Gerir Clientes")
        println("3. Gerir Marcas/Modelos")
        println("4. Gerir Tipos de Peças")
        println("5. Gerir Peças")
        println("0. Sair")
        println("=====================================")
        print("Escolha uma opção: ")
        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> menuAreaFuncionarios()
            2 -> menuClientes()
            3 -> menuMarcasModelos()
            4 -> menuTiposPecas()
            5 -> menuPecas()
            0 -> {
                println("A sair do Menu de Administração.")
                finalizar = true
            }
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun menuTiposPecas() {
    var finalizar = false
    while (!finalizar) {
        println("=====================================")
        println("Área de Tipos de Peças")
        println("=====================================")
        println("1. Adicionar Tipo de Peça")
        println("2. Listar Tipos de Peças")
        println("3. Editar Tipo de Peça")
        println("4. Remover Tipo de Peça")
        println("0. Sair")
        println("=====================================")
        print("Escolha uma opção: ")
        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> adicionarTipoPeca()
            2 -> listarTiposPecas()
            3 -> editarTipoPeca()
            4 -> removerTipoPeca()
            0 -> finalizar = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun adicionarTipoPeca() {
    println("Adicionar Tipo de Peça")
    print("Nome do Tipo de Peça: ")
    val nomeTipoPeca = readLine()!!

    val tipoPecaExistente = listaTipoPecas.find { it.nome.equals(nomeTipoPeca, ignoreCase = true) }
    if (tipoPecaExistente != null) {
        println("Já existe um tipo de peça com este nome. Operação cancelada.")
        return
    }

    val idTipoPeca = if (listaTipoPecas.isEmpty()) 1 else listaTipoPecas.maxOf { it.id } + 1

    val tipoPeca = TipoPeca(idTipoPeca, nomeTipoPeca)
    listaTipoPecas.add(tipoPeca)
    println("Tipo de Peça adicionado com sucesso!")
}

fun listarTiposPecas() {
    println("Listar Tipos de Peças")
    for (tipoPeca in listaTipoPecas) {
        println("ID: ${tipoPeca.id}, Nome: ${tipoPeca.nome}")
    }
}

fun editarTipoPeca() {
    println("Editar Tipo de Peça")
    listarTiposPecas()
    print("ID do Tipo de Peça: ")
    val idTipoPeca = readLine()!!.toInt()
    val tipoPeca = listaTipoPecas.find { it.id == idTipoPeca }
    if (tipoPeca != null) {
        println("Nome atual do Tipo de Peça: ${tipoPeca.nome}")
        print("Novo Nome do Tipo de Peça: ")
        val novoNome = readLine()!!
        tipoPeca.nome = novoNome
        println("Tipo de Peça editado com sucesso!")
    } else {
        println("Tipo de Peça não encontrado.")
    }
}

fun removerTipoPeca() {
    println("Remover Tipo de Peça")
    listarTiposPecas()
    print("ID do Tipo de Peça: ")
    val idTipoPeca = readLine()!!.toInt()
    val tipoPeca = listaTipoPecas.find { it.id == idTipoPeca }
    if (tipoPeca != null) {
        // Verificar se existem peças que usam este tipo
        val pecasComEsteTipo = listaPecas.filter { it.tipoPeca.id == idTipoPeca }
        if (pecasComEsteTipo.isNotEmpty()) {
            println("Não é possível remover este tipo de peça pois existem peças associadas a ele.")
            return
        }

        listaTipoPecas.remove(tipoPeca)
        println("Tipo de Peça removido com sucesso!")
    } else {
        println("Tipo de Peça não encontrado.")
    }
}

fun menuPecas() {
    var finalizar = false
    while (!finalizar) {
        println("=====================================")
        println("Área de Peças")
        println("=====================================")
        println("1. Adicionar Peça")
        println("2. Listar Peças")
        println("3. Editar Peça")
        println("4. Remover Peça")
        println("0. Sair")
        println("=====================================")
        print("Escolha uma opção: ")
        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> adicionarPeca()
            2 -> listarPecas()
            3 -> editarPeca()
            4 -> removerPeca()
            0 -> finalizar = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun adicionarPeca() {
    println("Adicionar Peça")
    print("Nome da Peça: ")
    val nomePeca = readLine()!!

    val pecaExistente = listaPecas.find { it.nome.equals(nomePeca, ignoreCase = true) }
    if (pecaExistente != null) {
        println("Já existe uma peça com este nome. Operação cancelada.")
        return
    }

    // Selecionar tipo de peça
    println("Escolha o Tipo de Peça pelo ID:")
    listarTiposPecas()
    print("ID do Tipo de Peça: ")
    val idTipoPeca = readLine()!!.toInt()

    val tipoPeca = listaTipoPecas.find { it.id == idTipoPeca }
    if (tipoPeca == null) {
        println("Tipo de Peça não encontrado. Peça não adicionada.")
        return
    }

    // Inserir preço
    print("Preço da Peça: ")
    val precoPeca = readLine()!!.toDouble()

    // Inserir stock
    print("Stock inicial da Peça: ")
    val stockPeca = readLine()!!.toInt()

    // Selecionar modelos compatíveis
    val modelosCompativeis = mutableListOf<Modelo>()
    var adicionarMaisModelos = true

    while (adicionarMaisModelos) {
        println("Escolha um Modelo compatível pelo ID (ou 0 para terminar):")
        listarModelos()
        print("ID do Modelo (0 para terminar): ")
        val idModelo = readLine()!!.toInt()

        if (idModelo == 0) {
            adicionarMaisModelos = false
        } else {
            val modelo = listaModelos.find { it.id == idModelo }
            if (modelo != null) {
                modelosCompativeis.add(modelo)
                println("Modelo adicionado à lista de compatibilidade.")
            } else {
                println("Modelo não encontrado.")
            }
        }
    }

    val idPeca = if (listaPecas.isEmpty()) 1 else listaPecas.maxOf { it.id } + 1

    val peca = Peca(idPeca, nomePeca, tipoPeca, precoPeca, stockPeca, modelosCompativeis)
    listaPecas.add(peca)
    println("Peça adicionada com sucesso!")
}

fun listarPecas() {
    println("Listar Peças")
    for (peca in listaPecas) {
        println(peca)
    }
}

fun editarPeca() {
    println("Editar Peça")
    print("ID da Peça: ")
    val idPeca = readLine()!!.toInt()
    val peca = listaPecas.find { it.id == idPeca }
    if (peca != null) {
        println("Peça encontrada:")
        println(peca)

        println("O que deseja editar?")
        println("1. Nome")
        println("2. Tipo de Peça")
        println("3. Preço")
        println("4. Stock")
        println("5. Modelos Compatíveis")
        println("0. Cancelar")
        print("Escolha uma opção: ")
        val opcao = readLine()!!.toInt()

        when (opcao) {
            1 -> {
                print("Novo Nome da Peça: ")
                val novoNome = readLine()!!
                peca.nome = novoNome
                println("Nome da peça atualizado com sucesso!")
            }
            2 -> {
                println("Escolha o novo Tipo de Peça pelo ID:")
                listarTiposPecas()
                print("ID do Tipo de Peça: ")
                val idTipoPeca = readLine()!!.toInt()
                val tipoPeca = listaTipoPecas.find { it.id == idTipoPeca }
                if (tipoPeca != null) {
                    peca.tipoPeca = tipoPeca
                    println("Tipo de peça atualizado com sucesso!")
                } else {
                    println("Tipo de Peça não encontrado.")
                }
            }
            3 -> {
                print("Novo Preço da Peça: ")
                val novoPreco = readLine()!!.toDouble()
                peca.preco = novoPreco
                println("Preço da peça atualizado com sucesso!")
            }
            4 -> {
                print("Novo Stock da Peça: ")
                val novoStock = readLine()!!.toInt()
                peca.stock = novoStock
                println("Stock da peça atualizado com sucesso!")
            }
            5 -> {
                // Atualizar modelos compatíveis
                val modelosCompativeis = mutableListOf<Modelo>()
                var adicionarMaisModelos = true

                while (adicionarMaisModelos) {
                    println("Escolha um Modelo compatível pelo ID (ou 0 para terminar):")
                    listarModelos()
                    print("ID do Modelo (0 para terminar): ")
                    val idModelo = readLine()!!.toInt()

                    if (idModelo == 0) {
                        adicionarMaisModelos = false
                    } else {
                        val modelo = listaModelos.find { it.id == idModelo }
                        if (modelo != null) {
                            modelosCompativeis.add(modelo)
                            println("Modelo adicionado à lista de compatibilidade.")
                        } else {
                            println("Modelo não encontrado.")
                        }
                    }
                }

                peca.compatibilidadeModelos = modelosCompativeis
                println("Modelos compatíveis atualizados com sucesso!")
            }
            0 -> println("Operação cancelada.")
            else -> println("Opção inválida.")
        }
    } else {
        println("Peça não encontrada.")
    }
}

fun removerPeca() {
    println("Remover Peça")
    listarPecas()
    print("ID da Peça: ")
    val idPeca = readLine()!!.toInt()
    val peca = listaPecas.find { it.id == idPeca }
    if (peca != null) {
        listaPecas.remove(peca)
        println("Peça removida com sucesso!")
    } else {
        println("Peça não encontrada.")
    }
}
