document.addEventListener("DOMContentLoaded", function () {
    const filtro = document.getElementById("filtroTipo");
    const cards = document.querySelectorAll(".animal-card");

    filtro.addEventListener("change", () => {
        const tipoSelecionado = filtro.value;

        cards.forEach(card => {
            const tipoAnimal = card.getAttribute("data-tipo");

            if (tipoSelecionado === "todos" || tipoAnimal === tipoSelecionado) {
                card.style.display = "block";
            } else {
                card.style.display = "none";
            }
        });
    });
});
