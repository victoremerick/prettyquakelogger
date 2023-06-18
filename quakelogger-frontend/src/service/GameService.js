import Api from "./Api";

export default async function fetchGames(callback) {
    try {
        const response = await Api.get('/');
        callback(response.data);
    } catch (error) {
        console.error('Get quake log error.', error);
    }
}