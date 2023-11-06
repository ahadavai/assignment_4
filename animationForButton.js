const addButtonElements = document.querySelectorAll(".addButton");

function fadeIn(element) {
    let opacity = 5;
    const interval = setInterval(function () {
        if (opacity < 1) {
            opacity += 0.1;
            element.style.opacity = opacity;
        } else {
            clearInterval(interval);
        }
    }, 100);
}

addButtonElements.forEach(button => {
    fadeIn(button);
});


const box = document.getElementById("box");
const moveButton = document.getElementById("move-button");

moveButton.addEventListener("click", () => {
    // Move the box to a new position
    box.style.transform = "translate(200px, 200px)";
});