const a = 5;
let b =5;

const myFunction = (foo) => {
    console.log(foo);
}

const getProducts = () => {
    return fetch("/api/products")
        .then(response => response.json());
}

const addToCart = (productId) => {
    return fetch(`/api/add-to-cart/${productId}`, {
        method: "POST",
        body: JSON.stringify({})
    }).then(response => response.json());
}

const createHtmlElementFromString = (template) => {
    let tmpElement = document.createElement('div');
    tmpElement.innerHTML = template.trim();

    return tmpElement.firstChild;
}

const createProductComponent = (product) => {
    const template = `
        <li class="product">
            <span>${product.name}</span>
            <div>
                <span>${product.price}</span>
            </div>
            <button
                class="product__add-to-cart"
                data-product-id="${product.id}"
            >
                Add to cart
            </button>
        </li>
    `;

    return createHtmlElementFromString(template);
}

const getCurrentOffer = () => {
    return fetch("/api/get-current-offer")
        .then(response => response.json());
}
const refreshCurrentOffer = () => {
    console.log('i am going to refresh offer');
    const offerElement = document.querySelector('.cart');

    getCurrentOffer()
        .then(offer => {
            offerElement.querySelector('.total').textContent = `${offer.total} PLN`;
            offerElement.querySelector('.itemsCount').textContent = `${offer.itemsCount} items`;
        });
}

const initializeAddToCartHandler = (el) => {
    const btn = el.querySelector('button.product__add-to-cart');
    btn.addEventListener('click', () => {
        addToCart(btn.getAttribute('data-product-id'))
            .then(refreshCurrentOffer())
    });

    return el;
}

(async () => {
    console.log("It works :)");
    const productsList = document.querySelector('#productsList');

    refreshCurrentOffer();

    const products = await getProducts();

    products
        .map(p => createProductComponent(p))
        .map(el => initializeAddToCartHandler(el))
        .forEach(el => productsList.appendChild(el));

    console.log("post get products");
})();




















