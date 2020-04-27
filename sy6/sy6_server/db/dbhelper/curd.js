module.exports={
      async find(model,condtion){
        let res = await model.find(condtion).exec();
        return res;
      },
      async create(model,condtion){
        await model.create(
            condtion,(err,doc)=>{
                if(err){
                    console.log(err);
                }else{
                    console.log("ok");
                    console.log(doc);
                }
            }
        );
       
      },
      async fuzzyFind(model,regx){
        let result= await model.find({$or:[{title:{$regex:new RegExp(regx)}}]}).exec();
        return result;
      }
    
}