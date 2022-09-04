import { criarMetaverso } from "./scripts.js";
//CSS
const categorias = document.querySelector ("#categorias");
const empresas = document.querySelector ("#empresas");
const plataformas = document.querySelector ("#plataformas");
const metaversos = document.querySelectorAll (".link-metaverso");

categorias.onclick = () => {
    categorias.classList.toggle ("bd-radius-select");
};

//INTEGRAÇÃO BACKEND

const containerMetaversos = document.querySelector (".container-metaversos");

const retorno = fetch ("https://searchverse-api.herokuapp.com/metaverses")
.then (data => {
    return data.json();
}).then (json => {
    for (let id in json) {
        containerMetaversos.appendChild (criarMetaverso (json[id]));
    }
})