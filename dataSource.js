const dataSource = [
    {
        imgSrc: "templates/images/cookie1.jpeg",
        title: "Chocolate Chip Cookie",
        description: "Delicious chocolate chip cookie.",
        price: "2.99",
    },
    {
        imgSrc: "templates/images/cookie2.jpg",
        title: "Oatmeal Raisin Cookie",
        description: "Soft and chewy oatmeal raisin cookie.",
        price: "3.49",
    },
    {
        imgSrc: "templates/images/cookie3.jpeg",
        title: "Double Chocolate Cookie",
        description: "Rich and indulgent double chocolate cookie.",
        price: "3.99",
    },
    {
        imgSrc: "templates/images/cookie4.jpeg",
        title: "Sugar Cookie",
        description: "Classic sugar cookie with a hint of vanilla.",
        price: "2.49",
    },
    {
        imgSrc: "templates/images/cookie5.jpg",
        title: "Peanut Butter Cookie",
        description: "Creamy and nutty peanut butter cookie.",
        price: "3.29",
    },
    {
        imgSrc: "templates/images/cookie6.jpeg",
        title: "Snickerdoodle Cookie",
        description: "Cinnamon and sugar-coated snickerdoodle cookie.",
        price: "2.79",
    },
];



const cookieItemsContainer = document.getElementById("cookieItems");

dataSource.forEach((cookie, index) => {
    const cookieItem = document.createElement("div");
    cookieItem.classList.add("col-lg-4", "col-md-6", "mb-4");
    cookieItem.setAttribute("data-cookie-id", index + 1);

    cookieItem.innerHTML = `
        <div class="card">
            <img src="${cookie.imgSrc}" class="card-img-top" alt="Cookie ${index + 1}">
            <div class="card-body">
                <h5 class="card-title">${cookie.title}</h5>
                <p class="card-text">${cookie.description}</p>
                <p class="card-text price">$${cookie.price}</p>
                <a id="addButton${index}" class="addButton btn btn-primary">Add to Cart</a>
            </div>
        </div>
    `;

    cookieItemsContainer.appendChild(cookieItem);
});

cookieItemsContainer.addEventListener("click", (event) => {
    if (event.target.classList.contains("addButton")) {
        const buttonId = event.target.id; 
        const index = buttonId.replace("addButton", ""); 
        const addToCartAudio = new Audio("templates/audio/zapsplat_household_alarm_clock_button_press_12967.mp3");
        addToCartAudio.play();
    }
});


const cartContainer = document.getElementById("cartItems");

const cartItems = [];

function updateCartDisplay() {
    cartContainer.innerHTML = "";

    cartItems.forEach((item, index) => {
        const cartItem = document.createElement("div");
        cartItem.classList.add("cart-item");
        cartItem.innerHTML = `
            <span>${item.title} - Price: $${item.price}</span>
            <button class="remove-button" data-index="${index}">Remove</button>
        `;
        cartContainer.appendChild(cartItem);
    });
}

cookieItemsContainer.addEventListener("click", (event) => {
    if (event.target.classList.contains("addButton")) {
        const buttonId = event.target.id; 
        const index = buttonId.replace("addButton", ""); 
        const item = dataSource[index];
        cartItems.push(item);

        updateCartDisplay();
    }
});

cartContainer.addEventListener("click", (event) => {
    if (event.target.classList.contains("remove-button")) {
        const index = event.target.getAttribute("data-index");
        cartItems.splice(index, 1);
        updateCartDisplay();
    }
});


// const cart = {};

// function addItemToCart(item) {
//     if (cart[item.title]) {
//         cart[item.title].quantity += 1;
//     } else {
//         cart[item.title] = {
//             item: item,
//             quantity: 1,
//         };
//     }

//     updateCartDisplay();
// }
