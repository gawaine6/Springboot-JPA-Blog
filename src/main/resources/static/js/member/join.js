let main = {
    init:function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
    },

    save:function () {
        alert('hello');
    }
}

main.init()