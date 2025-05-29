window.addEventListener("DOMContentLoaded", () => {
    const alerts = document.querySelectorAll(".alert-success");
    alerts.forEach((alert) => {
        setTimeout(() => {
            alert.classList.add("alert-hide");
            setTimeout(() => alert.remove(), 500);
        }, 3000);
    });
});
