const addToCartAudio = new Audio("templates/audio/zapsplat_household_alarm_clock_button_press_12967.mp3");

const addButton = document.getElementById("addButton1");

addButton.addEventListener("click", () => {
    addToCartAudio.play();
});
// document.addEventListener("DOMContentLoaded", () => {
//     const addToCartAudio = new Audio("templates/audio/zapsplat_household_alarm_clock_button_press_12967.mp3");
//     const addButton = document.getElementById("addButton");

//     addButton.addEventListener("click", () => {
//         addToCartAudio.play();
//     });
// });


const addButtonElements = document.querySelectorAll(".addButton");

addButtonElements.forEach(button => {

    button.addEventListener("click", () => {
        const addToCartAudio = new Audio("templates/audio/zapsplat_household_alarm_clock_button_press_12967.mp3");
    
            addToCartAudio.play();
       
    });
});

