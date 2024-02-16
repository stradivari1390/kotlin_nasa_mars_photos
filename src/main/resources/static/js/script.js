document.addEventListener('click', function(e) {
    if (e.target.classList.contains('photo-img')) {
        const modal = document.createElement('div');
        modal.classList.add('full-screen-modal');
        modal.innerHTML = `<img src="${e.target.src}" alt="Full Screen Mars Photo" style="width: 100%; height: auto;" />`;
        modal.style.position = 'fixed';
        modal.style.top = '0';
        modal.style.left = '0';
        modal.style.width = '100%';
        modal.style.height = '100%';
        modal.style.display = 'flex';
        modal.style.justifyContent = 'center';
        modal.style.alignItems = 'center';
        modal.style.backgroundColor = 'rgba(0,0,0,0.8)';
        modal.addEventListener('click', () => {
            document.body.removeChild(modal);
        });
        document.body.appendChild(modal);
    }
});

document.addEventListener('DOMContentLoaded', function () {
    var mySwiper = new Swiper('.mySwiper', {
        slidesPerView: 1,
        spaceBetween: 30,
        loop: true,
        centeredSlides: true,
        autoHeight: false,
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });
});