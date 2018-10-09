let equip_button = document.getElementById("equip_button");

//Equip button
equip_button.addEventListener("click", function () {
    let item = get_selected_item();
    if (item.type === "weapon") {
        requestEquipWeapon(item.name);
    } else if (item.type === "armor") {
        requestEquipArmor(item.name);
    } else if (item.type === "healthpotion") {
        requestPotion(item.name);
    }
});