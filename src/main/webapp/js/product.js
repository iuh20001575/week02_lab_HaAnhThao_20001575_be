const qtyInput = document.querySelector('.qty-input');
const qty = document.querySelector('input[name="qty"]');

const handleChangeQty = () => qty.value = qtyInput.value

qtyInput.addEventListener('change', handleChangeQty)
minusBtns[0].addEventListener('click', handleChangeQty)
plusBtns[0].addEventListener('click', handleChangeQty)