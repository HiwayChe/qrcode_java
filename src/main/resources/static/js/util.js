function getQueryParam() {
    let search = window.location.search;
    if(!search || search.length <= 1){
        return {};
    }
    let params = {};
    search.substring(1).split("&").forEach(s=>{
        let sp = s.split("=");
        if(params[sp[0]]){
            if(Array.isArray(params[sp[0]])){
                params[sp[0]].push(sp[1]);
            }else{
                params[sp[0]] = Array.of(params[sp[0]]);
            }
        }else{
            params[sp[0]] = sp[1];
        }
    })
    return params;
}