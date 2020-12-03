/**
 * 
 */
package it.esdebitamiretake.retake_ai.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Utente
 *
 */
public class GenericArrayListBean<Z> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Valid
	@NotNull
	@Size(min=1)
	private ArrayList<Z> items;
	
	public GenericArrayListBean() {
		super();
	}
	
	public ArrayList<Z> getItems() {
		if(Objects.isNull(this.items)) {
			this.items = new ArrayList<Z>(0);
		}
		return items;
	}

	public void setItems(ArrayList<Z> beans) {
		this.items = beans;
	}
}
