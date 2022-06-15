function generateColor() {
    let r = parseInt(Math.random() * 255);
    let g = parseInt(Math.random() * 255);
    let b = parseInt(Math.random() * 255);
    return `rgb(${r}, ${g}, ${b})`
}
function statsProductOptions(id, productLabels, productInfo) {
    let colors = []
    for (let i = 0; i < productInfo.length; i++) {
        colors.push(generateColor())
    }
    const data = {
        labels: productLabels,
        datasets: [{
            data: productInfo,
            backgroundColor: colors,
        }],
    };
    const config = {
        type: 'bar',
        data: data,
        options: {
            responsive: true,
        },
    }
    var ctx = document.getElementById(id).getContext("2d");
    new Chart(ctx, config);
}
function statsProduct(id, productLabels, productInfo) {
    let colors = []
    for (let i = 0; i < productInfo.length; i++) {
        colors.push(generateColor())
    }
    const data = {
        labels: productLabels,
        datasets: [{
            data: productInfo,
            backgroundColor: colors,
        }],
    };
    const config = {
        type: 'bar',
        data: data,
        options: {
            responsive: true,
        },
    }
    var ctx = document.getElementById(id).getContext("2d");
    new Chart(ctx, config);
}