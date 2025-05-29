document.addEventListener("DOMContentLoaded", () => {
    const tipoSelect = document.getElementById("tipo");
    const recintoSelect = document.getElementById("recinto");

    const mapaRecintos = window.mapaRecintos || {};

    tipoSelect.addEventListener("change", () => {
        const tipo = tipoSelect.value;
        recintoSelect.innerHTML = "";

        if (mapaRecintos[tipo]) {
            mapaRecintos[tipo].forEach(r => {
                const opt = document.createElement("option");
                opt.value = r.nome;
                opt.textContent = r.nome;
                recintoSelect.appendChild(opt);
            });
        } else {
            const opt = document.createElement("option");
            opt.value = "";
            opt.textContent = "Nenhum recinto disponível";
            recintoSelect.appendChild(opt);
        }
    });
});
