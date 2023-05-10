const a = 5;
let b = 5;

const myFunc = (foo) => {
    console.log(foo);

}

document.addEventListener("DOMContentLoaded", () => {
    console.log("this works after document has loaded")
});

(() => {
    console.log("it works")
})();