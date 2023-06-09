const getProducts = () => {
    return fetch("/api/products")
        .then((response) => response.json())
        .catch((error) => console.log(error))
};

const getCurrentOffer = () => {
    return fetch("/api/current-offer")
        .then((response) => response.json())
}

const handleAddToCart = (productId) => {
    return fetch(`/api/cart/${productId}`, {
        method: 'POST'
    });
};

const refreshCurrentOffer = async () => {
    const offer = await getCurrentOffer();
    const offerEl = document.querySelector('.offer');

    offerEl.querySelector('.total').textContent = `${offer.total} PLN`;
    offerEl.querySelector('.itemsCount').textContent = `${offer.productsCount} items`;

}

const createHtmlElFromString = (template) => {
    let parent = document.createElement("div");
    parent.innerHTML = template.trim();

    return parent.firstChild;
}

const createProductComponent = (product) => {
    const template = `
        <li class="product">
            <span class="product__description">${product.name}</span>
            <div class="product__image-container">
                <img class="product__image" src="${product.picture}"/>
            </div>
            <span class="product__price">${product.price}</span>
            <button
                class="product__add-to-cart"
                data-product-id="${product.id}"
            >
                Add to cart
            </button>
        </li>
    `;

    return createHtmlElFromString(template);
}

const initializeAddToCartHandler = (el) => {
    el.addEventListener('click', (e) => {
        let button = e.target;
        const productId = button.getAttribute('data-product-id');

        handleAddToCart(productId)
            .then(() => refreshCurrentOffer())
            .catch((error) => console.log(error))
        ;
    });

    return el;
}


const initializeEcommerce = async () => {
    await refreshCurrentOffer();

    const productsList = document.querySelector('#productsList');
    const products = await getProducts();
    products
        .map(p => createProductComponent(p))
        .map(productEl => initializeAddToCartHandler(productEl))
        .forEach(productEl => {
            productsList.appendChild(productEl)
        });

}


(() => {
    console.log("My ebook store works");
    initializeEcommerce()
        .then(r => {});
})();