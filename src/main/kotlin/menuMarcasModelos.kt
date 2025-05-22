
fun menuMarcasModelos() {
    var finalizar = false
    while (!finalizar) {
        println("Área de Marcas/Modelos")
        println("1. Marcas")
        println("2. Modelos")
        println("0. Sair")
        val opcao = readLine()!!.toInt()
        cls()
        when (opcao) {
            1 -> menuMarcas()
            2 -> menuModelos()
            0 -> finalizar = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun menuMarcas() {
    var finalizar = false
    while (!finalizar) {
        println("Área de Marcas")
        println("1. Adicionar Marca")
        println("2. Mostrar Marcas")
        println("3. Editar Marca")
        println("4. Remover Marca")
        println("0. Sair")
        print("Escolha uma opção: ")
        val opcao = readLine()!!.toInt()

        when (opcao) {
            1 -> adicionarMarca()
            2 -> mostrarMarcas()
            3 -> editarMarca()
            4 -> removerMarca()
            0 -> finalizar = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun adicionarMarca() {
    println("Adicionar Marca")
    print("Nome da Marca: ")
    val nomeMarca = readLine()!!
    val marcaExistente = listaMarcas.find { it.nome.equals(nomeMarca, ignoreCase = true) }
    if (marcaExistente != null) {
        println("Já existe uma marca com este nome. Operação cancelada.")
        return
    }

    val idMarca = if (listaMarcas.isEmpty()) 1 else listaMarcas.maxOf { it.id } + 1

    val marca = Marca(idMarca, nomeMarca)
    listaMarcas.add(marca)
    println("Marca adicionada com sucesso!")
}

fun mostrarMarcas() {
    println("Listar Marcas")
    for (marca in listaMarcas) {
        println(marca)
    }
}

fun editarMarca() {
    println("Editar Marca")
    print("ID da Marca: ")
    val idMarca = readLine()!!.toInt()
    val marca = listaMarcas.find { it.id == idMarca }
    if (marca != null) {
        println("Nome atual da Marca: ${marca.nome}")
        print("Novo Nome da Marca: ")
        val novoNome = readLine()!!
        marca.nome = novoNome
        println("Marca editada com sucesso!")
    } else {
        println("Marca não encontrada.")
    }
}

fun removerMarca(){
    println("Remover Marca")
    mostrarMarcas()
    print("ID da Marca: ")
    val idMarca = readLine()!!.toInt()
    val marca = listaMarcas.find { it.id == idMarca }
    if (marca != null) {
        listaMarcas.remove(marca)
        listaModelos.removeIf { it.marca.id == idMarca }
        println("Marca removida com sucesso!")
    } else {
        println("Marca não encontrada.")
    }
}

fun menuModelos() {
    var finalizar = false
    while (!finalizar) {
        println("Área de Modelos")
        println("1. Adicionar Modelo")
        println("2. Listar Modelos")
        println("3. Editar Modelo")
        println("4. Remover Modelo")
        println("0. Sair")
        print("Escolha uma opção: ")
        val opcao = readLine()!!.toInt()

        when (opcao) {
            1 -> adicionarModelo()
            2 -> mostrarModelo()
            3 -> editarModelo()
            4 -> removerModelo()
            0 -> finalizar = true
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

fun adicionarModelo() {
    println("Adicionar Modelo")
    print("Nome do Modelo: ")
    val nomeModelo = readLine()!!

    val modeloExistente = listaModelos.find { it.nome.equals(nomeModelo, ignoreCase = true) }
    if (modeloExistente != null) {
        println("Já existe um modelo com este nome. Operação cancelada.")
        return
    }

    println("Escolha a Marca pelo ID:")
    mostrarMarcas()
    print("ID da Marca: ")
    val idMarca = readLine()!!.toInt()

    val marca = listaMarcas.find { it.id == idMarca }
    if (marca != null) {
        val idModelo = if (listaModelos.isEmpty()) 1 else listaModelos.maxOf { it.id } + 1

        val modelo = Modelo(idModelo, nomeModelo, marca)
        listaModelos.add(modelo)
        println("Modelo adicionado com sucesso!")
    } else {
        println("Marca não encontrada. Modelo não adicionado.")
    }
}

fun mostrarModelo() {
    println("Listar Modelos")
    for (modelo in listaModelos) {
        println(modelo)
    }
}

fun editarModelo() {
    println("Editar Modelo")
    print("ID do Modelo: ")
    val idModelo = readLine()!!.toInt()
    val modelo = listaModelos.find { it.id == idModelo }
    if (modelo != null) {
        println("Nome atual do Modelo: ${modelo.nome}")
        print("Novo Nome do Modelo: ")
        val novoNome = readLine()!!
        modelo.nome = novoNome
        println("Modelo editado com sucesso!")
    } else {
        println("Modelo não encontrado.")
    }
}

fun removerModelo(){
    println("Remover Modelo")
    mostrarModelo()
    print("ID do Modelo: ")
    val idModelo = readLine()!!.toInt()
    val modelo = listaModelos.find { it.id == idModelo }
    if (modelo != null) {
        listaModelos.remove(modelo)
        println("Modelo removido com sucesso!")
    } else {
        println("Modelo não encontrado.")
    }
}