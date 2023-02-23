//Criação de evento para mudar o estilo da tela
const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener('click', () => {
    container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener('click', () => {
    container.classList.remove("sign-up-mode");
});
/////////////////////////////////////////////////////////////////////
//Script para conexão do cadastro do site com o backend (Springboot API)
// const formCadastro = document.querySelector(".signup-form");
// const Cusername = document.querySelector(".username-cadastro");
// const Cemail = document.querySelector(".email-cadastro");
// const Csenha = document.querySelector(".senha-cadastro");
// const CconfSenha = document.querySelector(".confirm-senha-cadastro");
// const Ccpf = document.querySelector(".cpf-cadastro");



function limpar(){
    Cusername.value = "";
    Cemail.value = "";
    CconfSenha.value = "";
    Csenha.value = "";
    Ccpf.value = "";
}

