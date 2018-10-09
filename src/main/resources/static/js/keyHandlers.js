document.onkeydown = function(e) {
    let handled;

    function callAction(fieldContent, direction) {
        if (mapCleared()) {
            requestNextLevel();
        } else {
            if (fieldContent === undefined) {
                requestMove(direction);
            } else if (fieldContent.name === "LOOT") {
                requestLoot(direction)
            } else if (fieldContent.name === "SLIME" ||
                fieldContent.name === "ORC" ||
                fieldContent.name === "SKELETON" ||
                fieldContent.name === "DRAGON") {
                requestFight(direction)
            }
        }
    }

    if (e.keyCode !== undefined && !defeat() && !wonTheGame()) {
        let fieldContent;
        switch (e.keyCode) {
            case 37: //left
                fieldContent = getLeftFieldContent();
                callAction(fieldContent, 'left');
                handled = true;
                break;
            case 39: //right
                fieldContent = getRightFieldContent();
                callAction(fieldContent, 'right');
                handled = true;
                break;
            case 38: //up
                fieldContent = getUpFieldContent();
                callAction(fieldContent, 'up');
                handled = true;
                break;
            case 40: //down
                fieldContent = getDownFieldContent();
                callAction(fieldContent, 'down');
                handled = true;
                break;
        }

        if (handled) {
            e.preventDefault();
        }
    }
};
