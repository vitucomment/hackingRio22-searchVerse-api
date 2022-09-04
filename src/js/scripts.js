//CSS
const categorias = document.querySelector ("#categorias");
const empresas = document.querySelector ("#empresas");
const plataformas = document.querySelector ("#plataformas");

categorias.onclick = () => {
    categorias.classList.toggle ("bd-radius-select");
};

//INTEGRAÇÃO BACKEND
const bBuscar = document.getElementById ("b-buscar");
const barraPesquisa = document.getElementById ("barra-pesquisa");

export function criarMetaverso (metaverso) {
    const container = document.createElement ("div");
    const containerExterno = document.createElement ("article");
    const containerInterno = document.createElement ("figure");
    const divImagem = document.createElement ("div");
    const descricao = document.createElement ("figcaption");
    const nomeMetaverso = document.createElement ("h2");
    const tags = document.createElement ("span");
    containerExterno.className = "metaverso";
    divImagem.className = "metaverso-imagem";
    divImagem.style.backgroundImage = "url(" + metaverso.img + ")";
    console.log (divImagem.style.backgroundImage);
    descricao.className = "metaverso-descricao";
    nomeMetaverso.className = "nome-metaverso";
    nomeMetaverso.innerText = metaverso.nome;
    tags.className = "tags";
    tags.innerHTML = metaverso.categoria;
    descricao.appendChild (nomeMetaverso);
    descricao.appendChild (tags);
    containerInterno.appendChild (divImagem);
    containerInterno.appendChild (descricao);
    containerExterno.appendChild (containerInterno);
    containerExterno.setAttribute ("data-id-bd", metaverso.id);
    container.appendChild (containerExterno);

    return container;
}

bBuscar.onclick = () => {
    const listaFiltros = {
        plataformas: "",
        empresas: "",
        categorias: "",
        pesquisa: ""
    };
    if (barraPesquisa) {
        listaFiltros.pesquisa = barraPesquisa.value;
    }
    if (plataformas) {
        listaFiltros.plataformas = plataformas.value;
    }
    if (empresas) {
        listaFiltros.empresas = empresas.value;
    }
    if (categorias) {
        listaFiltros.categorias = categorias.value;
    }
    localStorage.setItem ("filtros", JSON.stringify (listaFiltros));
}