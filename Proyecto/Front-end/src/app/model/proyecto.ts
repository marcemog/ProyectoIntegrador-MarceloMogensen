export class Proyecto {
    id?: number;
    nombrePro: string;
    descripcionPro: string;
    imgPro: string;

    constructor(nombrePro: string, descripcionPro: string,imgPro: string) {
        this.nombrePro=nombrePro;
        this.descripcionPro=descripcionPro;
        this.imgPro=imgPro;
    }
}
