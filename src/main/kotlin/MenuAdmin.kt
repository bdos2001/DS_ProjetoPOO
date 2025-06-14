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
        println("6. Gerir Fornecedores")
        println("0. Sair")
        println("=====================================")
        print("Escolha uma opção: ")
        try {
            val opcao = readLine()!!.toInt()
            cls()
            when (opcao) {
                1 -> menuAreaFuncionarios()
                2 -> menuClientes()
                3 -> menuMarcasModelos()
                4 -> menuTiposPecas()
                5 -> menuPecas()
                6 -> menuFornecedores()
                0 -> {
                    println("A sair do Menu de Administração.")
                    finalizar = true
                }
                else -> println("Opção inválida, tente novamente.")
            }
        } catch (e: NumberFormatException) {
            cls()
            println("Erro: Por favor, insira um número válido.")
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
        try {
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
        } catch (e: NumberFormatException) {
            cls()
            println("Erro: Por favor, insira um número válido.")
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
    try {
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
    } catch (e: NumberFormatException) {
        println("Erro: Por favor, insira um número válido para o ID.")
    }
}

fun removerTipoPeca() {
    println("Remover Tipo de Peça")
    listarTiposPecas()
    print("ID do Tipo de Peça: ")
    try {
        val idTipoPeca = readLine()!!.toInt()
        val tipoPeca = listaTipoPecas.find { it.id == idTipoPeca }
        if (tipoPeca != null) {
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
    } catch (e: NumberFormatException) {
        println("Erro: Por favor, insira um número válido para o ID.")
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
        try {
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
        } catch (e: NumberFormatException) {
            cls()
            println("Erro: Por favor, insira um número válido.")
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

    println("Escolha o Tipo de Peça pelo ID:")
    listarTiposPecas()
    print("ID do Tipo de Peça: ")
    try {
        val idTipoPeca = readLine()!!.toInt()

        val tipoPeca = listaTipoPecas.find { it.id == idTipoPeca }
        if (tipoPeca == null) {
            println("Tipo de Peça não encontrado. Peça não adicionada.")
            return
        }

        print("Preço da Peça: ")
        try {
            val precoPeca = readLine()!!.toDouble()

            print("Stock inicial da Peça: ")
            try {
                val stockPeca = readLine()!!.toInt()

                val modelosCompativeis = mutableListOf<Modelo>()
                var adicionarMaisModelos = true

                while (adicionarMaisModelos) {
                    println("Escolha um Modelo compatível pelo ID (ou 0 para terminar):")
                    listarModelos()
                    print("ID do Modelo (0 para terminar): ")
                    try {
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
                    } catch (e: NumberFormatException) {
                        println("Erro: Por favor, insira um número válido para o ID do modelo.")
                    }
                }

                val idPeca = if (listaPecas.isEmpty()) 1 else listaPecas.maxOf { it.id } + 1

                val peca = Peca(idPeca, nomePeca, tipoPeca, precoPeca, stockPeca, modelosCompativeis)
                listaPecas.add(peca)
                println("Peça adicionada com sucesso!")
            } catch (e: NumberFormatException) {
                println("Erro: Por favor, insira um número válido para o stock.")
            }
        } catch (e: NumberFormatException) {
            println("Erro: Por favor, insira um número válido para o preço.")
        }
    } catch (e: NumberFormatException) {
        println("Erro: Por favor, insira um número válido para o ID do tipo de peça.")
    }
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
    try {
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
            try {
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
                        try {
                            val idTipoPeca = readLine()!!.toInt()
                            val tipoPeca = listaTipoPecas.find { it.id == idTipoPeca }
                            if (tipoPeca != null) {
                                peca.tipoPeca = tipoPeca
                                println("Tipo de peça atualizado com sucesso!")
                            } else {
                                println("Tipo de Peça não encontrado.")
                            }
                        } catch (e: NumberFormatException) {
                            println("Erro: Por favor, insira um número válido para o ID do tipo de peça.")
                        }
                    }
                    3 -> {
                        print("Novo Preço da Peça: ")
                        try {
                            val novoPreco = readLine()!!.toDouble()
                            peca.preco = novoPreco
                            println("Preço da peça atualizado com sucesso!")
                        } catch (e: NumberFormatException) {
                            println("Erro: Por favor, insira um número válido para o preço.")
                        }
                    }
                    4 -> {
                        print("Novo Stock da Peça: ")
                        try {
                            val novoStock = readLine()!!.toInt()
                            peca.stock = novoStock
                            println("Stock da peça atualizado com sucesso!")
                        } catch (e: NumberFormatException) {
                            println("Erro: Por favor, insira um número válido para o stock.")
                        }
                    }
                    5 -> {
                        val modelosCompativeis = mutableListOf<Modelo>()
                        var adicionarMaisModelos = true

                        while (adicionarMaisModelos) {
                            println("Escolha um Modelo compatível pelo ID (ou 0 para terminar):")
                            listarModelos()
                            print("ID do Modelo (0 para terminar): ")
                            try {
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
                            } catch (e: NumberFormatException) {
                                println("Erro: Por favor, insira um número válido para o ID do modelo.")
                            }
                        }

                        peca.compatibilidadeModelos = modelosCompativeis
                        println("Modelos compatíveis atualizados com sucesso!")
                    }
                    0 -> println("Operação cancelada.")
                    else -> println("Opção inválida.")
                }
            } catch (e: NumberFormatException) {
                println("Erro: Por favor, insira um número válido para a opção.")
            }
        } else {
            println("Peça não encontrada.")
        }
    } catch (e: NumberFormatException) {
        println("Erro: Por favor, insira um número válido para o ID da peça.")
    }
}

fun removerPeca() {
    println("Remover Peça")
    listarPecas()
    print("ID da Peça: ")
    try {
        val idPeca = readLine()!!.toInt()
        val peca = listaPecas.find { it.id == idPeca }
        if (peca != null) {
            listaPecas.remove(peca)
            println("Peça removida com sucesso!")
        } else {
            println("Peça não encontrada.")
        }
    } catch (e: NumberFormatException) {
        println("Erro: Por favor, insira um número válido para o ID da peça.")
    }
}

fun menuFornecedores() {
    var finalizar = false
    while (!finalizar) {
        println("=====================================")
        println("Área de Fornecedores")
        println("=====================================")
        println("1. Adicionar Fornecedor")
        println("2. Listar Fornecedores")
        println("3. Editar Fornecedor")
        println("4. Remover Fornecedor")
        println("0. Sair")
        println("=====================================")
        print("Escolha uma opção: ")
        try {
            val opcao = readLine()!!.toInt()
            cls()
            when (opcao) {
                1 -> adicionarFornecedor()
                2 -> listarFornecedores()
                3 -> editarFornecedor()
                4 -> removerFornecedor()
                0 -> finalizar = true
                else -> println("Opção inválida, tente novamente.")
            }
        } catch (e: NumberFormatException) {
            cls()
            println("Erro: Por favor, insira um número válido.")
        }
    }
}

fun adicionarFornecedor() {
    println("Adicionar Fornecedor")
    print("Nome do Fornecedor: ")
    val nomeFornecedor = readLine()!!

    print("País do Fornecedor: ")
    val paisFornecedor = readLine()!!

    print("Número de Telefone do Fornecedor: ")
    val numeroTelefoneFornecedor = readLine()!!

    val fornecedorExistente = listaFornecedores.find { it.nome.equals(nomeFornecedor, ignoreCase = true) }
    if (fornecedorExistente != null) {
        println("Já existe um fornecedor com este nome. Operação cancelada.")
        return
    }

    val idFornecedor = if (listaFornecedores.isEmpty()) 1 else listaFornecedores.maxOf { it.id } + 1

    val fornecedor = Fornecedor(idFornecedor, nomeFornecedor, paisFornecedor, numeroTelefoneFornecedor)
    listaFornecedores.add(fornecedor)
    println("Fornecedor adicionado com sucesso!")
}

fun listarFornecedores() {
    println("Listar Fornecedores")
    if (listaFornecedores.isEmpty()) {
        println("Não existem fornecedores registados.")
        return
    }

    for (fornecedor in listaFornecedores) {
        println(fornecedor)
    }
}

fun editarFornecedor() {
    println("Editar Fornecedor")
    listarFornecedores()

    if (listaFornecedores.isEmpty()) {
        return
    }

    print("ID do Fornecedor: ")
    try {
        val idFornecedor = readLine()!!.toInt()
        val fornecedor = listaFornecedores.find { it.id == idFornecedor }

        if (fornecedor != null) {
            var finalizar = false
            while (!finalizar) {
                println("\nInformações atuais do fornecedor:")
                println(fornecedor)

                println("\nEscolha o campo para editar:")
                println("1. Nome")
                println("2. País")
                println("3. Número de Telefone")
                println("4. Finalizar edição")
                print("Opção: ")
                val opcao = readLine()!!

                when (opcao) {
                    "1" -> {
                        print("Novo nome: ")
                        fornecedor.nome = readLine()!!
                    }
                    "2" -> {
                        print("Novo país: ")
                        fornecedor.pais = readLine()!!
                    }
                    "3" -> {
                        print("Novo número de telefone: ")
                        fornecedor.numeroTelefone = readLine()!!
                    }
                    "4" -> {
                        finalizar = true
                        println("Fornecedor editado com sucesso!")
                    }
                    else -> println("Opção inválida, tente novamente.")
                }
            }
        } else {
            println("Fornecedor não encontrado.")
        }
    } catch (e: NumberFormatException) {
        println("Erro: Por favor, insira um número válido para o ID do fornecedor.")
    }
}

fun removerFornecedor() {
    println("Remover Fornecedor")
    listarFornecedores()

    if (listaFornecedores.isEmpty()) {
        return
    }

    print("ID do Fornecedor: ")
    try {
        val idFornecedor = readLine()!!.toInt()
        val fornecedor = listaFornecedores.find { it.id == idFornecedor }

        if (fornecedor != null) {
            print("Tem certeza que deseja remover o fornecedor ${fornecedor.nome}? (S/N): ")
            val confirmacao = readLine()!!
            if (confirmacao.equals("S", ignoreCase = true)) {
                listaFornecedores.remove(fornecedor)
                println("Fornecedor removido com sucesso!")
            } else {
                println("Operação cancelada.")
            }
        } else {
            println("Fornecedor não encontrado.")
        }
    } catch (e: NumberFormatException) {
        println("Erro: Por favor, insira um número válido para o ID do fornecedor.")
    }
}
