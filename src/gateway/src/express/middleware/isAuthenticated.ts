import { Request, Response, NextFunction } from "express";
import axios from "axios";

export async function isAuthenticatedMiddleware(req: Request, res: Response, next: NextFunction){
    const jwt = req.cookies.id;

    if(!jwt)
        return res.status(403).json({message: "Not Authorized"})

    else{
        await axios.post('http://localhost:1234/api/auth/verify', {
            token: jwt   
           }, {
               withCredentials: true
           }).then((response) => {
               if(response.data.valid == false){
                    return res.status(403).json({message: "Not Authorized"});
               }
           }).catch(() => {
            return res.json({message: "Error occurred"})
           })

        req.headers.authorization = `Bearer ${jwt}`
    }

    next();
}
