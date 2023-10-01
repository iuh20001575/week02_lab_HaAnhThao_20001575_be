const minusBtns = document.querySelectorAll('.minus');
const plusBtns = document.querySelectorAll('.plus');
const inputs = document.querySelectorAll('input[type="number"]');

minusBtns.forEach((minusBtn, index) => {
    minusBtn.onclick = () => {
        const value = +inputs[index].value;
        console.log(value)

        inputs[index].value = Math.max(value - 1, 1);
    };
})

plusBtns.forEach((plusBtn, index) => {
    plusBtn.onclick = () => {
        const value = +inputs[index].value;
        console.log(value)

        inputs[index].value = value + 1;
    }
})
