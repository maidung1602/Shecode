const form = document.querySelector(".custom-form");

const items = form.querySelectorAll(".item");
for (let i = 0; i < items.length; i++) {
    let icons = items[i].querySelectorAll("i");
    for (let j=0; j<5; j++) {
        icons[j].addEventListener("click", () => {
            handleClick(i, j);
        });
    }
}

function handleClick(m, n) {
    let icons = items[m].querySelectorAll("i");
    
    for (let j=0; j<=n; j++) {
        icons[j].classList.add("color-active");
    }
    let input = items[m].querySelector("input");
    input.value = n + 1;
    console.log(input.value);
}







