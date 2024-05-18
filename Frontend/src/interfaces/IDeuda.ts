export default interface IDeuda {
    id: number,
    tipo_deuda_id: number,
    comunidad_id: number,
    created_at: Date,
    updated_at: Date,
    cuantia: number
}