/*
 *  Copyright 2010 Hippo.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.onehippo.gogreen.jaxrs.model;

import java.util.List;

import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.ArrayUtils;
import org.hippoecm.hst.content.beans.standard.HippoResource;
import org.hippoecm.hst.content.rewriter.ContentRewriter;
import org.hippoecm.hst.core.container.ContainerConstants;
import org.hippoecm.hst.core.request.HstRequestContext;

import com.onehippo.gogreen.beans.Product;
import com.onehippo.gogreen.beans.compound.ImageSet;

/**
 * @version $Id$
 */
@XmlRootElement(name = "product")
public class ProductRepresentation extends DocumentRepresentation {
    
	private String productLink;
	private double price;
	private double rating;
	private double votes;
	private String[] categories;
	private String image;
	private String[] images;
	private String smallThumbnail;
	
	public ProductRepresentation(){}
	
	public ProductRepresentation(HstRequestContext requestContext, ContentRewriter<String> contentRewriter){
		super(requestContext, contentRewriter);
	}
    
    public ProductRepresentation represent(Product bean) throws RepositoryException {
        super.represent(bean);
        
    	//Its WRONG that there is jcr code here
        this.productLink = this.requestContext
        						.getHstLinkCreator()
        						.create(bean.getNode(), this.requestContext, ContainerConstants.MOUNT_ALIAS_SITE)
        						.toUrlForm(this.requestContext, true);
        
        this.price = bean.getPrice();
        this.rating = bean.getRating();
        this.votes = bean.getVotes();
        this.categories = (String []) ArrayUtils.clone(bean.getCategories());
        this.image = buildImageLinkUrl(bean);
        this.images = buildImageGalleryLinks(bean);
        this.smallThumbnail = buildImageLinkUrl(bean, "hippogogreengallery:smallthumbnail");
        
        return this;
    }

    
    @XmlElement(name="productLink")
    public String getProductLink(){
    	return productLink;
    }
    
    public double getPrice() {
        return price;
    }
	public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public double getVotes() {
		return votes;
	}
	public void setVotes(double votes) {
		this.votes = votes;
	}
	
    @XmlElementWrapper(name="categories")
    @XmlElements(@XmlElement(name="category"))
    public String[] getCategories() {
        return categories;
    }    
    
    public String getImage(){
    	return image;
    }
    
    public String getSmallThumbnail() {
        return smallThumbnail;
    }
    
    @XmlElementWrapper(name="images")
    @XmlElements(@XmlElement(name="image"))
    public String[] getImages() {
        return images;
    }   
    
    private String buildImageLinkUrl(Product product){
        if (product.getMainImage() == null) {
            return null;
        }
        
    	//Its WRONG that there is jcr code here 
    	return requestContext
    			.getHstLinkCreator()
    			.create(product.getMainImage().getNode(), this.requestContext, ContainerConstants.MOUNT_ALIAS_SITE)
    			.toUrlForm(this.requestContext, true);
    }
    
    private String buildImageLinkUrl(Product product, String relPath){
        ImageSet imageSet = product.getMainImage();
        
        if (imageSet == null) {
            return null;
        }
        
        HippoResource imageResource = imageSet.getBean(relPath);
        
        if (imageResource == null) {
            return null;
        }
        
        //Its WRONG that there is jcr code here 
        return requestContext
                .getHstLinkCreator()
                .create(imageResource.getNode(), this.requestContext, ContainerConstants.MOUNT_ALIAS_SITE)
                .toUrlForm(this.requestContext, true);
    }
    
    private String[] buildImageGalleryLinks(Product product){
    	List<ImageSet> images = product.getImages();
    	
    	if (images == null) {
    	    return null;
    	}
    	
    	String[] imageUrls = new String[images.size()];
    	for(int i=0; i<images.size(); i++) 
    		imageUrls[i] = requestContext
    			.getHstLinkCreator()
		    	//Its WRONG that there is jcr code here
    			.create(images.get(i).getNode(), this.requestContext, ContainerConstants.MOUNT_ALIAS_SITE)
    			.toUrlForm(this.requestContext, true);
    	
    	return imageUrls; 
    }    
    
}
