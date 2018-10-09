let mapObjects = [];
let mainCharacter;
let mainCharacterHealth;
let levelNumber;
let gameIsWon = false;

window.addEventListener('load', function() {
    ajax_get("/send", function (data) {
        mapObjects = data[0];
        mainCharacter = data[1][0];
        mainCharacterHealth = mainCharacter.hp;
        updateCharacterStats();
        updateInventory();
    }, 'load');
});

function preload() {
    loadImages();

}

function setup() {
    let cnv = createCanvas(490, 490);
    cnv.parent('canvas');
}

function draw() {
    clear();
    fill(230);
    rect(0, 0, 490, 490);
    drawBoard();
    }

function drawBoard() {
    for (let i = 0; i < mapObjects.length; i++) {
        let object = mapObjects[i];
        switch (object.name) {
            case 'DRAGON': image(dragon_image, object.x*50, object.y*50, height/12, width/12);
                break;
            case 'LOOT': image(loot_image, object.x*50, object.y*50, height/12, width/12);
                break;
            case 'MAIN_CHARACTER': image(main_character_image, object.x*50, object.y*50, height/12, width/12);
                break;
            case 'SKELETON': image(skeleton_image, object.x*50, object.y*50, height/12, width/12);
                break;
            case 'SLIME': image(slime_image, object.x*50, object.y*50, height/12, width/12);
                break;
            case 'WALL': image(wall_image, object.x*50, object.y*50, height/12, width/12);
                break;
            case 'ORC': image(orc_image, object.x*50, object.y*50, height/12, width/12)
        }
    }
    defeat();
    wonTheGame();
}

function getPlayerPosition() {
    let player = mapObjects.filter(o => o.name === "MAIN_CHARACTER")[0];
    let fromX = player.x;
    let fromY = player.y;
    return {fromX, fromY};
}

function getLeftFieldContent() {
    let {fromX, fromY} = getPlayerPosition();
    return mapObjects.filter(o => o.x === fromX - 1 && o.y === fromY)[0]
}
function getRightFieldContent() {
    let {fromX, fromY} = getPlayerPosition();
    return mapObjects.filter(o => o.x === fromX + 1 && o.y === fromY)[0]
}
function getUpFieldContent() {
    let {fromX, fromY} = getPlayerPosition();
    return mapObjects.filter(o => o.x === fromX && o.y === fromY - 1)[0]
}
function getDownFieldContent() {
    let {fromX, fromY} = getPlayerPosition();
    return mapObjects.filter(o => o.x === fromX && o.y === fromY + 1)[0]
}

function mapCleared() {
    for (let object of mapObjects) {
        if (!(object.name === "WALL" || object.name === "MAIN_CHARACTER")) {
            return false;
        }
    }
    return true;
}

function wonTheGame() {
    if (mapCleared() && levelNumber === 1) {
        image(won_image, 0, 0, 490, 490);
        if (!gameIsWon) {
            won();
        }
        gameIsWon = true;
        return true;
    }
    return false;
}