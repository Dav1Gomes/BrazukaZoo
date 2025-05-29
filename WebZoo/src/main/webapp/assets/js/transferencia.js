document.addEventListener("DOMContentLoaded", () => {
    const mapaRecintos = window.mapaRecintos || {};

    const selectAnimal = document.getElementById("idAnimal");
    const recintoInput = document.getElementById("recintoOrigem");

    if (selectAnimal && recintoInput) {
        selectAnimal.addEventListener("change", () => {
            const id = selectAnimal.value;
            recintoInput.value = mapaRecintos[id] || "";
        });
    }
});
