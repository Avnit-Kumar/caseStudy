// Smooth fade-in effect
document.addEventListener("DOMContentLoaded", function () {
    document.body.style.opacity = 0;
    document.body.style.transition = "opacity 0.4s ease-in";
    setTimeout(() => {
        document.body.style.opacity = 1;
    }, 100);
});

// Confirm before form submission (optional enhancement)
function confirmSubmit() {
    return confirm("Are you sure you want to add this employee?");
}