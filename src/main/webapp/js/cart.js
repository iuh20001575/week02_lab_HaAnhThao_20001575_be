const selectItems = document.querySelectorAll('.select-item');
const selectAll = document.querySelector('.select-all');
const productCount = document.querySelector('.product-count');
const price = document.querySelector('span.price');
const totalPrices = document.querySelectorAll('.total-price');
const prices = document.querySelectorAll('td.price');
const qtyInputs = document.querySelectorAll('.qty-input');

const handleClickCheckbox = () => {
    const selectedAll = document.querySelectorAll('.select-item:checked');
    const count = selectedAll.length;
    let prices = 0;

    selectedAll.forEach((selected) => {
        const price =
            +selected.parentElement.parentElement.querySelector('.total-price')
                .innerHTML;

        prices += price;
    });

    productCount.innerHTML = count;
    price.innerHTML = prices.toFixed(2);
};

selectAll.addEventListener('click', (e) => {
    const checked = selectAll.checked;

    e.stopPropagation();

    selectItems.forEach((selectItem) => {
        selectItem.checked = checked;
    });

    handleClickCheckbox();
});

selectItems.forEach((selectItem) => {
    selectItem.addEventListener('click', (e) => {
        const checked = selectItem.checked;
        e.stopPropagation();

        if (checked) {
            if (
                document.querySelectorAll('.select-item:checked').length ===
                selectItems.length
            )
                selectAll.checked = true;
        } else selectAll.checked = false;

        handleClickCheckbox();
    });
});

const postData = (productId, cartId, quantityInput) => {
    return new Promise((res, rej) =>
        fetch('api/cart-details', {
            method: 'put',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                product: {
                    product_id: productId,
                },
                cart: {
                    customer: {
                        id: cartId,
                    },
                },
                quantity: quantityInput,
            }),
        })
            .then(res)
            .catch(rej),
    );
};

const handleChangeQty = async (btn, index) => {
    const parentElement = btn.parentElement;
    const quantityInput = parentElement.querySelector('.qty-input');
    const { productId, cartId } = parentElement.dataset;
    const value = quantityInput.value;

    totalPrices[index].innerHTML = (+prices[index].innerHTML * +value).toFixed(
        2,
    );
    handleClickCheckbox();

    try {
        await postData(productId, cartId, value);
    } catch (e) {
        console.error(e);
    }
};

[...minusBtns, ...plusBtns].forEach((btn, index) => {
    btn.addEventListener('click', () =>
        handleChangeQty(btn, index % minusBtns.length),
    );
});

qtyInputs.forEach((qtyInput, index) => {
    qtyInput.addEventListener('change', () => handleChangeQty(qtyInput, index));
});
