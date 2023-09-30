const form = document.querySelector(".custom-form");

form.querySelectorAll(".item").forEach((item, index) => {
    item.setAttribute("name", "item"+index);
    item.querySelectorAll("i").setAttribute("name", "item"+index+"-name");
});