function defeat() {
    if (mainCharacterHealth < 1) {
        image(defeat_image, 0, 0, 490, 490);
        return true;
    }
    return false;
}