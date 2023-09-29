const minusBtn = document.querySelector('.minus');
const plusBtn = document.querySelector('.plus');
const input = document.querySelector('input[type="number"]');

minusBtn.addEventListener('click', () => {
    const value = +input.value;

    input.value = Math.max(value - 1, 1);
});

plusBtn.addEventListener('click', () => {
    const value = +input.value;

    input.value = value + 1;
});
