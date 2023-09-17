//initializeEcommerce is the first function (initialized at the bottom of index.js)
const initializeEcommerce = async () => {
    await refreshCurrentOffer(); //Downloads current offer and updates total price in PLN (using document.querySelecotor)
    const {
        host, hostname, href, origin, pathname, port, protocol, search
    } = window.location

    console.log(pathname);

    const productsList = document.querySelector('#productsList');
    const products = await getSortedProducts(pathname); //get products that match current pathname
    products.map(p => createProductComponent(p)) //product component looks exactly the same for every item,
        .map(productEl => initializeAddToCartHandler(productEl)) //search for button in productComponent and wait for click, and add total amount to cart
        .forEach(productEl => {
            productsList.appendChild(productEl)
        });

}

const refreshCurrentOffer = async () => {
    const offer = await getCurrentOffer();
    const offerEl = document.querySelector('.offer');

    offerEl.querySelector('.total').textContent = `${offer.total} PLN`;
    //offerEl.querySelector('.itemsCount').textContent = `${offer.productsCount} items`;

}

const getSortedProducts = async (descr) => {
    const products = await getProducts();
    let sortedProducts = []
    products.map(p => {
        if (p.desc == descr){
            sortedProducts.push(p);
            console.log(p.name)
        }
    })
    return sortedProducts;
}


const getCurrentOffer = () => {
    return fetch("/api/current-offer")
        .then((response) => response.json())
}

const getProducts = () => {
    return fetch("/api/products")
        .then((response) => response.json())
        .catch((error) => console.log(error))
};




const handleAddToCart = (productId) => {
    return fetch(`/api/cart/${productId}`, {
        method: 'POST'
    });
};



const createHtmlElFromString = (template) => {
    let parent = document.createElement("div");
    parent.innerHTML = template.trim();

    return parent.firstChild;
}

const createProductComponent = (product) => {
    const template = `
        <div>
        <div class="product-block">
            <div class="name">Buy ${product.name}</div>
            <div class="name-price">${product.price} PLN</div>
            <div class="options">
                 <div class="img-container">
                    <img src="${product.image}"/>
                </div>
                <div class="specs">
                    <div class="spec">
                        <span class="info">Personalize your ${product.name}</span>
                        <label class="container">
                          <input type="radio" checked="checked" name="radio">
                          <span class="checkmark"></span>
                          <span class="text">${product.name}</span>
                        </label>
                        <label class="container">
                          <input type="radio" name="radio">
                          <span class="checkmark"></span>
                          <span class="text">${product.name} Pro</span>
                        </label>
                   </div>
                   <div class="spec">
                        <span class="info">Choose your storage</span>
                        <label class="container">
                          <input type="radio" checked="checked" name="radio2">
                          <span class="checkmark"></span>
                          <span class="text">128 GB</span>
                        </label>
                        <label class="container">
                          <input type="radio" name="radio2">
                          <span class="checkmark"></span>
                          <span class="text">256 GB</span>
                        </label>
                        <label class="container">
                          <input type="radio" name="radio2">
                          <span class="checkmark"></span>
                          <span class="text">512 GB</span>
                        </label>
                 </div>
                 </div>
     </div>
     </div>
         <div class="buy">
              <div>
              <div class="title">Your new ${product.name}</div>
              <div class="price">${product.price} PLN</div>
              </div>
             <button data-product-id="${product.id}">Add to cart</button>
         </div>
     </div>            
    `;

    return createHtmlElFromString(template);
}

const initializeAddToCartHandler = (el) => {
    const addToCartButton = el.querySelector('button')
    addToCartButton.addEventListener('click', (e) => {
        let button = e.target;
        const productId = button.getAttribute('data-product-id');

        handleAddToCart(productId)
            .then(() => refreshCurrentOffer())
            .catch((error) => console.log(error))
        ;
    });

    return el;
}



const acceptOfferBtn = document.querySelector('.acceptOffer');
const checkoutLayerEl = document.querySelector('#checkout');
const checkoutForm = document.querySelector('#checkout form');
checkoutForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const data = new FormData(checkoutForm);
    let request = {};
    for (let [key, value] of data) {
        request[key] = value;
    }

    fetch("/api/accept-offer", {
        method: 'POST',
        body: JSON.stringify(request),
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(r => r.json())
        .then(data => window.location.href = data.paymentUrl);
})
acceptOfferBtn.addEventListener('click', () => {
    checkoutLayerEl.classList.add('shown');
});

(() => {
    initializeEcommerce()
        .then(r => {});
})();