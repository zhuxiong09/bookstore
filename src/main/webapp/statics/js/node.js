/*
给body设一个id号，获取节点后传入该方法在加载事件中执行可一次性解决页面中所有空节点的干扰。
如果只想去除某个节点下所有子节点中的空白节点，则只需传入对应的节点
*/
function clearWhitespace(clean_node){
	var node_arr=clean_node.childNodes;
	for(var i=0;i<node_arr.length;i++){
			if(typeof(node_arr[i].innerHTML)=="undefined" && !/\S/.test(node_arr[i].nodeValue)){
				node_arr[i].parentNode.removeChild(node_arr[i]);
				i--;
			}else{
				var child_node_arr=node_arr[i].childNodes;
				if(child_node_arr.length==0){
					continue;
				}else{
					clearWhitespace(node_arr[i]);	
				}
			}
		}
}
    