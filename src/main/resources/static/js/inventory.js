let character_inventory_list = document.getElementById("list-tab");
let character_inventory_list_descriptions = document.getElementById("nav-tabContent");

function updateInventory() {
    while (character_inventory_list.firstChild) {
        character_inventory_list.removeChild(character_inventory_list.firstChild);
    }

    while (character_inventory_list_descriptions.firstChild) {
        character_inventory_list_descriptions.removeChild(character_inventory_list_descriptions.firstChild);
    }

    for (let item of mainCharacter.inventory) {
        let item_info;
        if (item.type === "weapon") {
            item_info = "+ " + item.minDamage + " - " + item.maxDamage + " damage";
        } else if (item.type === "armor") {
            item_info = "+ " + item.healthIncrease + " HP";
        } else if (item.type === "healthpotion") {
            item_info = "+ " + item.healing + " HP";
        }

        //inventory item list
        character_inventory_list.innerHTML +=
            '<a class="list-group-item list-group-item-action" id="list-'
            + item.name.replace(/ /g,'') + '-list" data-toggle="list" href="#list-' + item.name.replace(/ /g,'') + '" role="tab" aria-controls="'
            + item.name.replace(/ /g,'') + '">' + item.name + '</a>';


        //description of the items
        character_inventory_list_descriptions.innerHTML +=
            '<div class="tab-pane fade" id="list-' + item.name.replace(/ /g,'') + '" role="tabpanel" aria-labelledby="list-' + item.name.replace(/ /g,'') + '-list">'
            + item_info + '</div>'
    }
    //setting the first list item selected
    //document.getElementById("list-tab").firstChild.classList.add("active");
}

    function get_selected_item() {
    let items = character_inventory_list.getElementsByClassName("list-group-item list-group-item-action");
    for (let item of items) {
        if (item.classList.contains("active")) {
            let itemName = item.innerHTML;
            for (let inventoryItem of mainCharacter.inventory) {
                if (inventoryItem.name === itemName) {
                    return inventoryItem;
                }
            }
        }
    }
}
