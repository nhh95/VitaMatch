document.querySelectorAll('.category-item').forEach(function (item) {
    item.addEventListener('click', function (event) {
        event.preventDefault();

        document.querySelectorAll('.category-item').forEach(function (el) {
            el.classList.remove('selected');
        });

        this.classList.add('selected');

        const category = this.getAttribute('data-category'); 
        const url = `?category=${encodeURIComponent(category)}`; 
        window.location.href = url;
    });
});

window.onload = function () {
    const params = new URLSearchParams(window.location.search);
    const currentCategory = params.get('category');
    if (currentCategory) {
        document.querySelectorAll('.category-item').forEach(function (item) {
            if (item.getAttribute('data-category') === currentCategory) {
                item.classList.add('selected');
            }
        });
    }
};