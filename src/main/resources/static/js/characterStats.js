let character_stats_list = document.getElementById("character_stats_list");
let response_field = document.getElementById("response_field");
let order_of_stats = ["Health", "Damage", "Strength", "Agility",  "Intellect"];

function updateCharacterStats(response) {

    while (character_stats_list.firstChild) {
        character_stats_list.removeChild(character_stats_list.firstChild);
    }

    for (let stat of order_of_stats) {
        let new_list_item = document.createElement('li');
        new_list_item.classList.add('list-group-item');
        let new_list_text = document.createTextNode(stat + ': ' + mainCharacter[stat]);
        new_list_item.appendChild(new_list_text);
        character_stats_list.appendChild(new_list_item);
    }

    response_field.innerHTML = response;
}
